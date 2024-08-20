package com.example.letstreat.UIPages.authUIpages

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letstreat.datum.PreferencesManager
import com.example.letstreat.R
import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthState
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel

@Composable
fun GetStartedView(
    navController : NavController,
    authViewModel: AuthViewModel,
    preferencesManager: PreferencesManager,
    displayItemsViewModel: DisplayItemsViewModel
) {

    val preManCurrentEmail = preferencesManager.getString("currentEmail")

    val context = LocalContext.current
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value){
        when(authState.value){
            is AuthState.Authenticated -> {
                if (preManCurrentEmail != null) {
                    authViewModel.isAdmin(preManCurrentEmail){
                        authViewModel.currentEmail.value = preManCurrentEmail
                        if(it){
                            authViewModel.isUserLoggedIn.value = false
                            authViewModel.assignCurrentHotelDetails()
                            navController.navigate(Screen.AdminBottomScreen.AdminHome.route)
                        } else{
                            authViewModel.isUserLoggedIn.value = true
                            authViewModel.assignCurrentUserDetails()
                            navController.navigate(Screen.UserBottomScreen.UserHome.bRoute)
                        }
                    }
                    displayItemsViewModel.storeAllUserFoodItems()
                }

            }
            is AuthState.Error -> {
                Toast.makeText(context,(authState.value as AuthState.Error).error,Toast.LENGTH_SHORT).show()
            }
            else -> Unit
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()

    ){
        Box {
            Image(
                painter = painterResource(id = R.drawable.getstartedbgimage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ){
            Button(
                onClick = {
                    navController.navigate(Screen.LoginPageView.route)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green3)
                ),
                border = BorderStroke(1.dp, colorResource(id = R.color.green4))
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white)
                )
            }
        }

    }
}