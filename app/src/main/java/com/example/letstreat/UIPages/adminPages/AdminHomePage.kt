package com.example.letstreat.UIPages.adminPages

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthState
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminHomePage(
    authViewModel: AuthViewModel,
    displayItemsViewModel: DisplayItemsViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    val authState by authViewModel.authState.observeAsState()

    var searchBarText by remember {
        mutableStateOf("")
    }

    var dynamicHeightForFoodItem by remember {
        mutableStateOf(100.dp)
    }

    var retrievedFoodItems by remember { mutableStateOf(emptyList<FoodItemDetails>()) }

    LaunchedEffect(retrievedFoodItems){
        val noOfFoodItems = retrievedFoodItems.size
        dynamicHeightForFoodItem = 185.dp * noOfFoodItems
    }

    displayItemsViewModel.retrieveAllAdminFoodChildElements(
        "HotelFoodItemDetails/${authViewModel.retrievedCurrentHotelDetails.value?.hotelName}${authViewModel.retrievedCurrentHotelDetails.value?.hotelContactNo}",
        { foodItemDetails ->
            // Success callback
            retrievedFoodItems = foodItemDetails
        },
        { databaseError ->
            // Failure callback
            Toast.makeText(context, "Error Occurred: ${databaseError.message}", Toast.LENGTH_SHORT).show()
        }
    )

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.UnAuthenticated -> navController.navigate(Screen.GetStartedView.route)
            else -> Unit
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbg)) // Set background color here
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize() // Ensure LazyColumn uses the full size of the Box
        ) {
            item {
                //This is to cover edge to edge Screen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }

            item {
                //SearchBar
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ){
                    OutlinedTextField(
                        value = searchBarText,
                        onValueChange = {
                            searchBarText = it
                        },
                        modifier = Modifier
                            .height(55.dp)
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor = colorResource(id = R.color.darkdarkblue),
                            unfocusedBorderColor = colorResource(id = R.color.darkdarkblue),
                            focusedBorderColor = colorResource(id = R.color.whitesmoke),
                            unfocusedPlaceholderColor = colorResource(id = R.color.whitesmoke),
                            focusedTextColor = colorResource(id = R.color.whitesmoke)
                        ),
                        placeholder = {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    modifier = Modifier.padding(end = 8.dp)
                                )
                                Text(
                                    text = "Search your interesting food"
                                )
                            }
                        },
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }

            item {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    Card(
                        modifier = Modifier
                            .size(160.dp, 200.dp)
                            .clip(RoundedCornerShape(25.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green3)
                        )
                    ) {
                        Text(
                            text = "Rounded Box",
                            color = Color.White
                        )
                    }
                    Card(
                        modifier = Modifier
                            .size(160.dp, 200.dp)
                            .clip(RoundedCornerShape(25.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green3)
                        )
                    ) {
                        Text(
                            text = "Rounded Box",
                            color = Color.White
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Previous menus",
                    color = colorResource(id = R.color.whitesmoke),
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp)
                )
            }

//            item {
//                Column {
//                    authViewModel.retrievedCurrentHotelDetails.value?.hotelName?.let {
//                        Text(
//                            text = it,
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//                    authViewModel.retrievedCurrentHotelDetails.value?.hotelContactNo?.let {
//                        Text(
//                            text = it,
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//
//                    authViewModel.retrievedCurrentHotelDetails.value?.hotelLocation?.let {
//                        Text(
//                            text = it,
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//
//                    authViewModel.retrievedCurrentHotelDetails.value?.hotelRating?.let {
//                        Text(
//                            text = it.toString(),
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//
//                    authViewModel.retrievedCurrentHotelDetails.value?.adminEmail?.let {
//                        Text(
//                            text = it,
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//
//                    authViewModel.retrievedCurrentHotelDetails.value?.adminPassword?.let {
//                        Text(
//                            text = it,
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//
//                    authViewModel.retrievedCurrentHotelDetails.value?.isPureVeg?.let {
//                        Text(
//                            text = it.toString(),
//                            color = colorResource(id = R.color.whitesmoke),
//                            modifier = Modifier.padding(top = 16.dp, start = 24.dp)
//                        )
//                    }
//
//                }
//            }

            item {
//                Text(text = retrievedFoodItems.toString())
                LazyColumn(
                    modifier = Modifier
                        .height(dynamicHeightForFoodItem)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    items(retrievedFoodItems){
                            item ->
                        Log.d("FoodItems", "Image URL: ${item.foodItemImageUrl}")
                        FoodItems(
                            item = item,
                            navController = navController
                        )
                    }
                }
            }
            
            

            item {

                Button(
                    onClick = {
                        authViewModel.signOut()
                    },
                    modifier = Modifier
                        .padding(16.dp) // Add padding to avoid edge-to-edge placement
                ) {
                    Text(text = "Sign Out")
                }

                Spacer(modifier = Modifier.height(40.dp))

            }

        }
    }
}