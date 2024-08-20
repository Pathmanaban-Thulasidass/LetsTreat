package com.example.letstreat.navigation

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.letstreat.R
import com.example.letstreat.datum.HotelDetails
import com.example.letstreat.viewModels.AuthViewModel

@Composable
fun AdminInfoNavigationIcon(
    authViewModel: AuthViewModel
) {

    var currentHotelName by remember {
        mutableStateOf("Saranya's Hotel")
    }

    var currentHotelLocation by remember {
        mutableStateOf("Periyar Nagar 2nd cross")
    }

//    var currentLoggedInHotelDetail : HotelDetails? = null

//    LaunchedEffect(authViewModel.retrievedCurrentHotelDetails.value){
//        currentLoggedInHotelDetail = authViewModel.retrievedCurrentHotelDetails.value
//    }

    val currentLoggedInHotelDetail : HotelDetails? = authViewModel.retrievedCurrentHotelDetails.value



    currentLoggedInHotelDetail?.let {
        if(it.hotelName != null && it.hotelLocation != null){
            currentHotelName = it.hotelName
            currentHotelLocation = it.hotelLocation
        }
    }


    Row(
        modifier = Modifier
            .height(80.dp)
            .width(370.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .padding(8.dp)
                .align(Alignment.CenterVertically),
            tint = colorResource(id = R.color.whitesmoke)
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = currentHotelName)
            Text(text = currentHotelLocation)
        }
    }


}