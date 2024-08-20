package com.example.letstreat.viewModels

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.datum.HotelDetails
import com.example.letstreat.objects.FileStorage
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.tasks.await

class DisplayItemsViewModel : ViewModel() {

    var totalNoOfItemsInCart = mutableIntStateOf(0)

    @SuppressLint("MutableCollectionMutableState")
    var totalNoOfItemsInCartArr = mutableStateOf<MutableList<Int>>(mutableListOf())

    init {
        totalNoOfItemsInCartArr.value = MutableList(100) { 1 }
    }

    val currentReviewFoodItemName = mutableStateOf("")
    val currentSuggestionFoodItemName = mutableStateOf("")

    var noOfStarsClickedInReview = mutableIntStateOf(0)


    var totalFoodFeeAmountInCart = mutableDoubleStateOf(0.0)

    private val database = FirebaseDatabase.getInstance()

    var retrievedFoodItems =  mutableStateOf(emptyList<FoodItemDetails>())

    fun addFoodItemDetails(
        foodItemDetailsPara : FoodItemDetails,
        hotelContact : String,
        onResult : (Boolean) -> Unit
    ){

        //For Admin Use
        val hotelFoodItemDetailsRef = database.reference.child("HotelFoodItemDetails")
        val singleHotelDetailsRef = hotelFoodItemDetailsRef.child(foodItemDetailsPara.foodItemHotelName + hotelContact)
        val singleAdminFoodItemDetails = singleHotelDetailsRef.child(
            foodItemDetailsPara.foodItemHotelName + foodItemDetailsPara.foodItemName
        )

        singleAdminFoodItemDetails.setValue(foodItemDetailsPara)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener{
                onResult(false)
            }

        //For User Use
        val allFoodItems = database.reference.child("AllFoodItems")
        val singleFoodItemDetailsRef = allFoodItems.child(
            foodItemDetailsPara.foodItemHotelName + "_" + foodItemDetailsPara.foodItemName
        )
        singleFoodItemDetailsRef.setValue(foodItemDetailsPara)
            .addOnSuccessListener {
                onResult(true)
            }
            .addOnFailureListener {
                onResult(false)
            }


    }


    fun retrieveAllAdminFoodChildElements(
        parentPath: String,
        onSuccess: (List<FoodItemDetails>) -> Unit,
        onFailure: (DatabaseError) -> Unit
    ) {
        val databaseRef = FirebaseDatabase.getInstance().getReference(parentPath)

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<FoodItemDetails>()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        val item = childSnapshot.getValue(FoodItemDetails::class.java)
                        if (item != null) {
                            items.add(item)
                        } else {
                            Log.w("Firebase", "Null item at ${childSnapshot.key}")
                        }
                    }
                    onSuccess(items)
                } else {
                    Log.d("Firebase", "No data available at $parentPath")
                    onSuccess(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database error: ${error.message}")
                onFailure(error)
            }
        })
    }

    fun retrieveASingleFoodItem(
        path: String,
        onResult: (FoodItemDetails?) -> Unit, // Callback to handle the result
    ) {
        val databaseRef = FirebaseDatabase.getInstance().reference.child("AllFoodItems").child(path)

        databaseRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val dataSnapshot = task.result
                val foodItemDetails = dataSnapshot.getValue(FoodItemDetails::class.java)
                if (foodItemDetails != null) {
                    onResult(foodItemDetails)
                } else {
                    onResult(null)
                }
            } else {
                onResult(null)
            }
        }
    }

    private fun retrieveAllUserFoodItems(
        parentPath: String,
        onSuccess: (List<FoodItemDetails>) -> Unit,
        onFailure: (DatabaseError) -> Unit
    ) {
        val databaseRef = FirebaseDatabase.getInstance().getReference(parentPath)

        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = mutableListOf<FoodItemDetails>()
                if (snapshot.exists()) {
                    for (childSnapshot in snapshot.children) {
                        val item = childSnapshot.getValue(FoodItemDetails::class.java)
                        if (item != null) {
                            items.add(item)
                        } else {
                            Log.w("Firebase", "Null item at ${childSnapshot.key}")
                        }
                    }
                    onSuccess(items)
                } else {
                    Log.d("Firebase", "No data available at $parentPath")
                    onSuccess(emptyList())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "Database error: ${error.message}")
                onFailure(error)
            }
        })
    }

    fun storeAllUserFoodItems()
    {
        retrieveAllUserFoodItems(
            "AllFoodItems",
            {
                retrievedFoodItems.value = it
            },{
                retrievedFoodItems.value = emptyList()
            }
        )
    }


    suspend fun uploadImageToFirebase(
        uri: Uri,
        fileName: String
    ): String {
        val storageRef = Firebase.storage.reference.child("images/$fileName")
        return try {
            storageRef.putFile(uri).await()
            val downloadUrl = storageRef.downloadUrl.await()
            downloadUrl.toString()
        } catch (e: Exception) {
            throw e
        }
    }


}