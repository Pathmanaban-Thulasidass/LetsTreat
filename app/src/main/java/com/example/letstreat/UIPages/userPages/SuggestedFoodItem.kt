package com.example.letstreat.UIPages.userPages

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel
import kotlin.math.ceil

@Composable
fun SuggestedFoodItem(
    foodCategoryName : String,
    displayItemsViewModel: DisplayItemsViewModel,
    navController: NavController
) {


    var retrievedFoodItems by remember { mutableStateOf(emptyList<FoodItemDetails>()) }

    retrievedFoodItems = displayItemsViewModel.retrievedFoodItems.value

    var dynamicHeightForFoodItem by remember {
        mutableStateOf(100.dp)
    }

    var sortedFoodItems by remember {
        mutableStateOf(emptyList<FoodItemDetails>())
    }

    val mutableSortedFoodItems = mutableListOf<FoodItemDetails>()

    retrievedFoodItems.forEach {
        if(it.foodItemCategory == foodCategoryName){
            mutableSortedFoodItems.add(it)
        }
    }

    sortedFoodItems = mutableSortedFoodItems

    LaunchedEffect(sortedFoodItems){
        val noOfFoodItems = sortedFoodItems.size
        dynamicHeightForFoodItem = 310.dp * ceil(noOfFoodItems / 2.0).toInt()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbg)) // Set background color here
    ){
        LazyColumn(
            modifier = Modifier.fillMaxSize()
            // Ensure LazyColumn uses the full size of the Box
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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(dynamicHeightForFoodItem)
                        .padding(12.dp)
                ){
                    items(sortedFoodItems){
                        ShowFoodItem(
                            foodItem = it,
                            navController = navController,
                            displayItemsViewModel = displayItemsViewModel
                        )
                    }
                }
            }

        }

    }

}