package com.example.letstreat.UIPages.authUIpages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letstreat.datum.PreferencesManager
import com.example.letstreat.R
import com.example.letstreat.datum.UserDetails

import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthState
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpPageView(
    authViewModel: AuthViewModel,
    navController: NavController,
    preferencesManager : PreferencesManager,
    displayItemsViewModel: DisplayItemsViewModel
) {

    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    var toastMsg by remember {
        mutableStateOf("")
    }

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Authenticated -> {
                displayItemsViewModel.storeAllUserFoodItems()
                navController.navigate(Screen.UserBottomScreen.UserHome.bRoute)
            }
            is AuthState.Error -> {
                if((authState.value as AuthState.Error).error == "The given password is invalid. [ Password should be at least 6 characters ]"){
                    toastMsg = "Password should be at least 6 characters"
                }
                else if((authState.value as AuthState.Error).error == "The email address is badly formatted."){
                    toastMsg = "The email address is badly formatted"
                }
                if(toastMsg.isNotEmpty()) {
                    Toast.makeText(context, toastMsg, Toast.LENGTH_SHORT).show()
                }
            }
            else -> Unit
        }
    }

    var userName by remember {
        mutableStateOf("")
    }

    var userLocation by remember {
        mutableStateOf("")
    }

    var userEmail by remember {
        mutableStateOf("")
    }

    var userPassword by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorResource(id = R.color.darkbg)
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .padding(16.dp)
                .padding(top = 36.dp)
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.signupimage),
                    contentDescription = "LoginPage Bg Image",
                    modifier = Modifier.padding(30.dp),
                    alignment = Alignment.Center
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
//                .border(BorderStroke(2.dp, color = colorResource(id = R.color.black)))
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = userName,
                    onValueChange = {
                        userName = it
                    },
                    label = {
                        Text(text = "Enter your Name")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.green3),
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = colorResource(id = R.color.green3),
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = userLocation,
                    onValueChange = {
                        userLocation = it
                    },
                    label = {
                        Text(text = "Enter your Location")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.green3),
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = colorResource(id = R.color.green3),
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = userEmail,
                    onValueChange = {
                        userEmail = it
                    },
                    label = {
                        Text(text = "Enter your Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.green3),
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = colorResource(id = R.color.green3),
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = userPassword,
                    onValueChange = {
                        userPassword = it
                    },
                    label = {
                        Text(text = "Set your password")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.green3),
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = colorResource(id = R.color.green3),
                        unfocusedBorderColor = Color.White
                    )

                )

                Spacer(modifier = Modifier.height(16.dp))

                Row (
                    modifier = Modifier.width(270.dp),
                    horizontalArrangement = Arrangement.End
                ){

                    Button(

                        onClick = {



                            val newUserDetails = UserDetails(
                                userEmail = userEmail ,
                                userPassword = userPassword,
                                userLocation = userLocation ,
                                userName = userName
                            )

                            authViewModel.addUserDetailsToFB(newUserDetails)

                            preferencesManager.saveString("currentEmail",userEmail)
                            authViewModel.currentEmail.value = preferencesManager.getString("currentEmail")!!


                            authViewModel.assignCurrentUserDetails()
                            authViewModel.isUserLoggedIn.value = true

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.green3)
                        ),
                        enabled = authState.value != AuthState.Loading && userEmail.isNotEmpty() && userPassword.isNotEmpty() && userName.isNotEmpty() && userLocation.isNotEmpty()
                    ) {
                        Text(
                            text = "Create Account",
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.whitesmoke)
                        )
                    }
                }

                TextButton(
                    onClick = {
                        navController.navigate(Screen.LoginPageView.route)
                    }
                ) {
                    Text(
                        text = "Already having an account, login",
                        color = colorResource(id = R.color.green3)    
                    )
                }

            }
        }
    }
}