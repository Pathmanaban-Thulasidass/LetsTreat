package com.example.letstreat.UIPages.authUIpages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letstreat.datum.PreferencesManager
import com.example.letstreat.R
import com.example.letstreat.datum.HotelDetails
import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthState
import com.example.letstreat.viewModels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HotelRegistrationView(
    navController: NavController,
    authViewModel : AuthViewModel,
    preferencesManager : PreferencesManager
) {

    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate(Screen.AdminBottomScreen.AdminHome.route)
            is AuthState.Error -> {
                Toast.makeText(context,(authState.value as AuthState.Error).error, Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }


    var hotelName by remember { mutableStateOf("") }
    var hotelLocation by remember { mutableStateOf("") }
    var hotelIsPureVeg by remember { mutableStateOf("") }
    var hotelPhoneNo by remember { mutableStateOf("") }
    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }
    var hotelRating by remember { mutableStateOf("") }
    var hotelOwnerContactNo by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        colorResource(id = R.color.green1),
//                        colorResource(id = R.color.green2),
//                        colorResource(id = R.color.green3),
//                        colorResource(id = R.color.green4)
//                    ),
//                    startY = 300f
//                )
                color = colorResource(id = R.color.darkbg)
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
                .padding(top = 36.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hotelregisterbgimage),
                    contentDescription = "LoginPage Bg Image",
                    modifier = Modifier
                        .height(250.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())  // Scrollable content
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = hotelName,
                    onValueChange = {
                        hotelName = it
                    },
                    label = {
                        Text(text = "Enter your Hotel Name")
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
                    value = hotelLocation,
                    onValueChange = {
                        hotelLocation = it
                    },
                    label = {
                        Text(text = "Enter your Hotel Location")
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
                    value = hotelPhoneNo,
                    onValueChange = {
                        hotelPhoneNo = it
                    },
                    label = {
                        Text(text = "Enter your Hotel Contact number")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone  // Use phone keyboard
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
                    value = hotelIsPureVeg,
                    onValueChange = {
                        hotelIsPureVeg = it
                    },
                    label = {
                        Text(text = "Enter is your Hotel pureveg")
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
                    value = hotelRating,
                    onValueChange = {
                        hotelRating = it
                    },
                    label = {
                        Text(text = "Enter your Hotel rating")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal  // Use Decimal keyboard
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
                    value = hotelOwnerContactNo,
                    onValueChange = {
                        hotelOwnerContactNo = it
                    },
                    label = {
                        Text(text = "Enter owner contact number")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number  // Use password keyboard
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
                        Text(text = "Enter your email")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email  // Use email keyboard
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
                        Text(text = "Set your account password")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password  // Use password keyboard
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = colorResource(id = R.color.green3),
                        unfocusedLabelColor = Color.White,
                        focusedLabelColor = colorResource(id = R.color.green3),
                        unfocusedBorderColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(

                    onClick = {

                        val newHotelDetails = HotelDetails(
                            hotelName = hotelName,
                            hotelLocation = hotelLocation,
                            hotelContactNo = hotelPhoneNo,
                            isPureVeg = hotelIsPureVeg.toLowerCase() == "yes",
                            hotelRating = hotelRating.toDouble(),
                            ownerContactNo = hotelOwnerContactNo,
                            adminPassword = userPassword,
                            adminEmail = userEmail
                        )

                        authViewModel.addHotelDetailsToFB(newHotelDetails)

                        preferencesManager.saveString("currentEmail",userEmail)

                        val preManCurrentEmail = preferencesManager.getString("currentEmail")

                        if(preManCurrentEmail != null){
                            authViewModel.currentEmail.value = preManCurrentEmail
                        }

                        authViewModel.isUserLoggedIn.value = false
                        authViewModel.assignCurrentHotelDetails()



                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green3)
                    ),
                    enabled = authState.value != AuthState.Loading
                ) {
                    Text(
                        text = "Register",
                        color = colorResource(id = R.color.whitesmoke)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}