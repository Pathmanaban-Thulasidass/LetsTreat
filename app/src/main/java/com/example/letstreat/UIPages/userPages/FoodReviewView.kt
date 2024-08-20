package com.example.letstreat.UIPages.userPages

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.navigation.Screen
import com.example.letstreat.objects.FileStorage
import com.example.letstreat.viewModels.DisplayItemsViewModel


@Composable
fun FoodReviewView(
    path: String,
    displayItemsViewModel: DisplayItemsViewModel,
    navController: NavController
) {

    val context = LocalContext.current
    var foodItemsInCart by remember {
        mutableStateOf(emptyList<FoodItemDetails>())
    }

    foodItemsInCart = FileStorage.loadFoodItemList(context).toList()


    var foodItem by remember { mutableStateOf<FoodItemDetails?>(null) }

    displayItemsViewModel.retrieveASingleFoodItem(path = path) {
        foodItem = it
    }

    LaunchedEffect(foodItemsInCart.size){
        displayItemsViewModel.totalNoOfItemsInCart.intValue = foodItemsInCart.size

        displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue = 0.0
        for(i in foodItemsInCart.indices){
            displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue += foodItemsInCart[i].foodItemPrice!! * displayItemsViewModel.totalNoOfItemsInCartArr.value[i]
        }

    }

    var recommendedHotelFoodItems by remember {
        mutableStateOf(emptyList<FoodItemDetails>())
    }

    displayItemsViewModel.retrieveAllAdminFoodChildElements(
        "HotelFoodItemDetails/${foodItem?.foodItemHotelName}${foodItem?.foodHotelContactNo}",
        {
            recommendedHotelFoodItems = it
        },
        {

        }
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.darkbg))
            .padding(16.dp)
    ) {

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }

        item {
            //Image
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ){
                foodItem?.let { item ->
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.foodItemImageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .border(BorderStroke(2.dp, colorResource(id = R.color.green3)))
                    )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

        }

        item {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colorResource(id = R.color.darkbg))
                    .padding(16.dp)
            ){
                Text(
                    text = foodItem?.foodItemHotelName.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = foodItem?.foodItemHotelLocation.toString(),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "₹"+foodItem?.foodItemPrice.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.green3),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "When it comes to food delivery, nothing beats the convenience of having a delicious meal brought straight from the kitchen to your doorstep. With a focus on speed and freshness, every bite is a taste delivered with care, ensuring your meal arrives prompt and satisfying.",
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                //Rating Stars
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    IconButton(
                        onClick = {
                            displayItemsViewModel.noOfStarsClickedInReview.intValue = 1;
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp),
                            tint = if(displayItemsViewModel.noOfStarsClickedInReview.intValue >= 1)
                                colorResource(id = R.color.green3)
                            else
                                Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            displayItemsViewModel.noOfStarsClickedInReview.intValue = 2;
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp),
                            tint = if(displayItemsViewModel.noOfStarsClickedInReview.intValue >= 2)
                                colorResource(id = R.color.green3)
                            else
                                Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            displayItemsViewModel.noOfStarsClickedInReview.intValue = 3;
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp),
                            tint = if(displayItemsViewModel.noOfStarsClickedInReview.intValue >= 3)
                                colorResource(id = R.color.green3)
                            else
                                Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            displayItemsViewModel.noOfStarsClickedInReview.intValue = 4;
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp),
                            tint = if(displayItemsViewModel.noOfStarsClickedInReview.intValue >= 4)
                                colorResource(id = R.color.green3)
                            else
                                Color.White
                        )
                    }
                    IconButton(
                        onClick = {
                            displayItemsViewModel.noOfStarsClickedInReview.intValue = 5;
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            modifier = Modifier
                                .size(30.dp),
                            tint = if(displayItemsViewModel.noOfStarsClickedInReview.intValue >= 5)
                                colorResource(id = R.color.green3)
                            else
                                Color.White
                        )
                    }
                }

            }
            
            Spacer(modifier = Modifier.height(4.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(
                    onClick = { 
                        //TODO back navigation and toast
                        Toast.makeText(context,"Thanks for giving Feedback",Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.green3)
                    )
                ) {
                    Text(
                        text = "Rate this",
                        color = colorResource(id = R.color.whitesmoke)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Recommended Food for you",
                textDecoration = TextDecoration.Underline
            )
            
            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                modifier = Modifier
                    .height(300.dp)
            ) {
                items(recommendedHotelFoodItems) { foodItem ->
                    ShowFoodItem(
                        foodItem = foodItem,
                        navController = navController,
                        displayItemsViewModel = displayItemsViewModel
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    navController.navigate(Screen.UserBottomScreen.UserCart.route)

                    foodItem?.let {
                        FileStorage.addFoodItem(
                            context = context,
                            newItem = it
                        )
                    }

                    displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue = 0.0

                    for(item in foodItemsInCart){
                        displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue += item.foodItemPrice!!
                    }
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
                        text = "Add to Cart",
                        color = colorResource(id = R.color.whitesmoke),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

        }
        
    }
}
