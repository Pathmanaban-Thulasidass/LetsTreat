package com.example.letstreat.UIPages.userPages

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.navigation.Screen
import com.example.letstreat.objects.FileStorage
import com.example.letstreat.viewModels.DisplayItemsViewModel


@Composable
fun CartView(
    displayItemsViewModel: DisplayItemsViewModel,
    navController: NavController
) {

    var foodItemsInCart by remember {
        mutableStateOf(emptyList<FoodItemDetails>())
    }


    val context = LocalContext.current

    LaunchedEffect(FileStorage.loadFoodItemList(context)){
        foodItemsInCart = FileStorage.loadFoodItemList(context).toList()

    }

    LaunchedEffect(foodItemsInCart.size){
        displayItemsViewModel.totalNoOfItemsInCart.intValue = foodItemsInCart.size

        if(foodItemsInCart.isEmpty()){
            navController.navigateUp()
        }

        displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue = 0.0
        for(i in foodItemsInCart.indices){
            displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue += foodItemsInCart[i].foodItemPrice!! * displayItemsViewModel.totalNoOfItemsInCartArr.value[i]
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbg)) // Set background color here
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {
                //This is to cover edge to edge Screen
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                )
            }


            items(foodItemsInCart) {
                item ->
                CartItemView(
                    item = item,
                    index = foodItemsInCart.indexOf(item),
                    context = context,
                    navController = navController,
                    displayItemsViewModel = displayItemsViewModel
                )
            }

        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .padding(start = 16.dp, end = 16.dp)
//                .border(BorderStroke(2.dp, Color.White))
        ) {

            Column {

                Divider(
                    color = colorResource(id = R.color.whitesmoke),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                )

                Column {

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total No. of Item",
                            color = colorResource(id = R.color.whitesmoke),
                            fontSize = 20.sp
                        )
                        Text(
                            text = displayItemsViewModel.totalNoOfItemsInCart.intValue.toString(),
                            color = colorResource(id = R.color.green3),
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Delivery Fee",
                            color = colorResource(id = R.color.whitesmoke),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "₹10",
                            color = colorResource(id = R.color.green3),
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Food Items Cost",
                            color = colorResource(id = R.color.whitesmoke),
                            fontSize = 20.sp
                        )
                        Text(
//
                            text = "₹" + "%.2f".format(displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue),
                            color = colorResource(id = R.color.green3),
                            fontSize = 20.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total ",
                            color = colorResource(id = R.color.whitesmoke),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "₹" + "%.2f".format(displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue + 10),
                            color = colorResource(id = R.color.green3),
                            fontSize = 20.sp
                        )
                    }

                }

                Spacer(modifier = Modifier.height(48.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    onClick = {
                         navController.navigate(Screen.FoodSummaryView.route)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green3)
                    ),
                    border = BorderStroke(1.dp, colorResource(id = R.color.green4))
                ) {
                    Box (
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(
                            text = "Place Order",
                            color = colorResource(id = R.color.whitesmoke),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

    }


}