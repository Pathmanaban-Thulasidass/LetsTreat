package com.example.letstreat.UIPages.userPages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthState
import com.example.letstreat.viewModels.AuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAccount(
    authViewModel: AuthViewModel,
    navController: NavController
) {

    val retrievedCurrentUserDetail = authViewModel.retrievedCurrentUserDetail.value!!

    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.UnAuthenticated -> navController.navigate(Screen.GetStartedView.route)
            else -> Unit
        }
    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ){

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
            )
            
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Username : ",
            fontSize = 20.sp,
            color = colorResource(id = R.color.green3)
        )


        OutlinedTextField(
            value = retrievedCurrentUserDetail.userName,
            onValueChange = {},
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                disabledTextColor = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )


        Text(
            text = "Location : ",
            fontSize = 20.sp,
            color = colorResource(id = R.color.green3),
            modifier = Modifier
                        .padding(top = 8.dp)
        )


        OutlinedTextField(
            value = retrievedCurrentUserDetail.userLocation,
            onValueChange = {},
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                disabledTextColor = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )


        Text(
            text = "Email : ",
            fontSize = 20.sp,
            color = colorResource(id = R.color.green3),
            modifier = Modifier
                .padding(top = 8.dp)
        )


        OutlinedTextField(
            value = retrievedCurrentUserDetail.userEmail,
            onValueChange = {},
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                disabledTextColor = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )


        Text(
            text = "Password : ",
            fontSize = 20.sp,
            color = colorResource(id = R.color.green3),
            modifier = Modifier
                .padding(top = 8.dp)
        )


        OutlinedTextField(
            value = retrievedCurrentUserDetail.userPassword,
            onValueChange = {},
            enabled = false,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.LightGray,
                disabledTextColor = colorResource(id = R.color.black)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ){

            Button(
                onClick = {
                    authViewModel.signOut()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green3)
                )
            ) {
                Text(
                    text = "Sign Out",
                    color = colorResource(id = R.color.whitesmoke),
                    fontSize = 20.sp
                )
            }

        }

    }

}