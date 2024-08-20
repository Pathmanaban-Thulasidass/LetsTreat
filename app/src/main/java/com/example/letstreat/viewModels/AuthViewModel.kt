package com.example.letstreat.viewModels

import android.util.Log
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.letstreat.datum.HotelDetails
import com.example.letstreat.datum.UserDetails
import com.example.letstreat.navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel: ViewModel() {


    var currentEmail = mutableStateOf("pathu@gmail_com")

    var retrievedCurrentUserDetail = mutableStateOf<UserDetails?>(null)
    var retrievedCurrentHotelDetails = mutableStateOf<HotelDetails?>(null)

    var isUserLoggedIn = mutableStateOf(true)

    var isReady = mutableStateOf(false)

    private val database = FirebaseDatabase.getInstance()

    private val auth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()

    val authState : LiveData<AuthState> = _authState


    init {
        checkAuthStatus()
        if(authState.value == AuthState.Authenticated){
            isAdmin(currentEmail.value){
                if(it){
                    isUserLoggedIn.value = false
                    assignCurrentHotelDetails()
//                    navController.navigate(Screen.AdminBottomScreen.AdminHome.route)
                } else{
                    isUserLoggedIn.value = true
                    assignCurrentUserDetails()
//                    navController.navigate(Screen.UserBottomScreen.UserHome.bRoute)
                }
            }
        }
    }

    fun checkAuthStatus() : Boolean{
        if(auth.currentUser == null){
            _authState.value = AuthState.UnAuthenticated
            return false
        }
        else{
            println("Pathu " + auth.currentUser)
            _authState.value = AuthState.Authenticated
            return true
        }
    }

    fun logIn(
        email : String,
        password : String
    ){

        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                }
                else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "something Went Wrong")
                }
            }
    }

    private fun signUp(
        email : String,
        password : String,
        isSuccess : (Boolean) -> Unit
    ){
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{
                    task ->
                if(task.isSuccessful){
                    _authState.value = AuthState.Authenticated
                    isSuccess(true)
                }
                else{
                    _authState.value = AuthState.Error(task.exception?.message ?: "something Went Wrong")
                    isSuccess(false)
                }
            }
    }

    fun signOut(){
        auth.signOut()
        _authState.value = AuthState.UnAuthenticated
    }

    fun addHotelDetailsToFB(hotelDetailsPara: HotelDetails) {

        hotelDetailsPara.adminEmail?.let {
            hotelDetailsPara.adminPassword?.let { it1 ->
                signUp(it, it1){
                    if(it){
                        val adminCredentialsRef = database.reference.child("AdminCredentials")
                        val sanitizedEmail = sanitizeEmail(hotelDetailsPara.adminEmail)
                        val singleAdminCredentialsRef = adminCredentialsRef.child(sanitizedEmail)

                        singleAdminCredentialsRef.setValue(hotelDetailsPara)
                            .addOnFailureListener { exception ->
                                _authState.value = AuthState.Error(exception.message ?: "Failed to add hotel details")
                            }
                    }
                }
            }
        }

    }

    private fun sanitizeEmail(email: String): String {
        return email.replace(".", "_")
            .replace("#", "_")
            .replace("$", "_")
            .replace("[", "_")
            .replace("]", "_")
    }

    fun isAdmin(path: String, callback: (Boolean) -> Unit) {

        val sanitizedEmailPath = sanitizeEmail(path)
        val ref = database.reference.child("AdminCredentials").child(sanitizedEmailPath)

        ref.addListenerForSingleValueEvent(
            object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    // Check if the snapshot exists
                    callback(snapshot.exists())
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle potential errors
                    Log.e("AuthViewModel", "Database error: ${error.message}")
                    callback(false)
                }
            }
        )
    }

    fun addUserDetailsToFB(userDetailsPara : UserDetails){

        signUp(userDetailsPara.userEmail,userDetailsPara.userPassword){
            if(it){
                val userCredentialsRef = database.reference.child("UserCredentials")
                val sanitizedEmail = sanitizeEmail(userDetailsPara.userEmail)
                val singleUserCredentialsRef = userCredentialsRef.child(sanitizedEmail)

                singleUserCredentialsRef.setValue(userDetailsPara)
                    .addOnFailureListener { exception ->
                        _authState.value = AuthState.Error(exception.message ?: "Failed to store User credentials")
                    }
            }
        }
    }


    fun assignCurrentUserDetails(){

        //currentUserEmail.value

        Log.d("userEmail : ", currentEmail.value)

        val sanitizedEmail = sanitizeEmail(currentEmail.value)
        val contactsRef = database.reference.child("UserCredentials").child(sanitizedEmail)

        contactsRef.get()
            .addOnSuccessListener { dataSnapshot ->
                val userDetail = dataSnapshot.getValue(UserDetails::class.java)
                if (userDetail != null) {
                    retrievedCurrentUserDetail.value = userDetail
                }
            }
            .addOnFailureListener {
                println("Failed to read user contact details")
            }
    }

    fun assignCurrentHotelDetails(){

        Log.d("userEmail ::::: ", currentEmail.value)

        val sanitizedEmail = sanitizeEmail(currentEmail.value)
        val contactsRef = database.reference.child("AdminCredentials").child(sanitizedEmail)

        contactsRef.get()
            .addOnSuccessListener { dataSnapshot ->
                val hotelDetail = dataSnapshot.getValue(HotelDetails::class.java)
                if (hotelDetail != null) {
                    retrievedCurrentHotelDetails.value = hotelDetail
                }
            }
            .addOnFailureListener {
                println("Failed to read user contact details")
            }

    }


}

sealed class AuthState{
    data object Authenticated : AuthState()
    data object UnAuthenticated : AuthState()
    data object Loading : AuthState()
    data class Error(val error : String) : AuthState()
}