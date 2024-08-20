package com.example.letstreat.UIPages.userPages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
fun ShowFoodItem(
    foodItem : FoodItemDetails,
    navController: NavController,
    displayItemsViewModel: DisplayItemsViewModel
) {

    val context = LocalContext.current
    var foodItemsInCart by remember {
        mutableStateOf(emptyList<FoodItemDetails>())
    }

    foodItemsInCart = FileStorage.loadFoodItemList(context).toList()


    Spacer(modifier = Modifier.height(16.dp))



    Card(
        modifier = Modifier
            .size(220.dp, 290.dp)
            .padding(top = 24.dp, start = 3.dp, end = 3.dp)
            .clickable {
                //TODO navigate to rating View
                navController.navigate(Screen.FoodItemReviewPage.route + "/${foodItem.foodItemHotelName}_${foodItem.foodItemName}")
                displayItemsViewModel.currentReviewFoodItemName.value = foodItem.foodItemName.toString()
            },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.whitesmoke)
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        //Image Box
        Column {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
                    .background(colorResource(id = R.color.darkbg))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                colorResource(id = R.color.darkbg),
                                Color.Transparent,
                                colorResource(id = R.color.whitesmoke)
                            ),
                            startY = 70f
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(foodItem.foodItemImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(130.dp, 130.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp)
                    .padding(8.dp)
            ) {
                Column {
                    foodItem.foodItemName?.let {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.black),
                            textDecoration = TextDecoration.Underline,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    foodItem.foodItemHotelName?.let {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.black)
                        )
                    }

                    foodItem.foodItemHotelLocation?.let {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.darkbg)
                        )
                    }

                    TextButton(
                        modifier = Modifier.padding(
                            start = 80.dp
                        ),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.darkbg)
                        )
                    ) {
                        Text(
                            text = "₹${foodItem.foodItemPrice.toString()}",
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.whitesmoke)
                        )
                    }

                }

            }

            TextButton(
                onClick = {
                    //TODO  navigate to cart view
                    navController.navigate(Screen.UserBottomScreen.UserCart.route)

                    FileStorage.addFoodItem(
                        context = context,
                        newItem = foodItem
                    )

                    displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue = 0.0

                    for(item in foodItemsInCart){
                        displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue += item.foodItemPrice!!
                    }

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green3)
                ),
                shape = RoundedCornerShape(0.dp)
            ) {
                Text(
                    text = "Add to Cart",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = colorResource(id = R.color.whitesmoke)
                )
            }


        }

    }


}