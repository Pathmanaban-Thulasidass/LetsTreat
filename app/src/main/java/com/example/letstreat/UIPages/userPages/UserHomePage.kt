package com.example.letstreat.UIPages.userPages

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.navigation.Screen
import com.example.letstreat.navigation.SuggestedFoodsInScreen
import com.example.letstreat.viewModels.AuthState
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel
import kotlin.math.ceil

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserHomePage(
    authViewModel: AuthViewModel,
    navController: NavController,
    displayItemsViewModel: DisplayItemsViewModel
) {

    var retrievedFoodItems by remember { mutableStateOf(emptyList<FoodItemDetails>()) }

    val authState by authViewModel.authState.observeAsState()

    var searchBarText by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    

    retrievedFoodItems = displayItemsViewModel.retrievedFoodItems.value

    var dynamicHeightForFoodItem by remember {
        mutableStateOf(100.dp)
    }

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.UnAuthenticated -> navController.navigate(Screen.GetStartedView.route)
            else -> Unit
        }
    }

    LaunchedEffect(retrievedFoodItems){
        val noOfFoodItems = retrievedFoodItems.size
        dynamicHeightForFoodItem = 310.dp * ceil(noOfFoodItems / 2.0).toInt()
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
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Card(
                        modifier = Modifier
                            .size(360.dp, 200.dp)
                            .clip(RoundedCornerShape(35.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green3)
                        ),
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
                    text = "Whats's on your mind?   ${authViewModel.currentEmail.value}",
                    color = colorResource(id = R.color.whitesmoke),
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                LazyHorizontalGrid(
                    rows = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) {
                    items(SuggestedFoodsInScreen) {
                        item ->
                            Column(
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .clickable {
                                        //TODO navigate to suggested Food
                                        navController.navigate(item.sfRoute)
                                        displayItemsViewModel.currentSuggestionFoodItemName.value = item.sfRoute
                                    },
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = item.img),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(70.dp)
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = item.sfRoute,
                                    fontSize = 14.sp
                                )
                            }
                    }
                }
            }

            item {
                Text(
                    text = "Do you taste this?",
                    color = colorResource(id = R.color.whitesmoke),
                    modifier = Modifier.padding(top = 16.dp, start = 24.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
//                Text(text = retrievedFoodItems.toString())
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(dynamicHeightForFoodItem)
                        .padding(12.dp)
                ){
                    items(retrievedFoodItems){
                        ShowFoodItem(
                            foodItem = it,
                            navController = navController,
                            displayItemsViewModel = displayItemsViewModel
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
