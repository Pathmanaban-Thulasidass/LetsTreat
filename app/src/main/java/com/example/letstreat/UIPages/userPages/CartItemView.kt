package com.example.letstreat.UIPages.userPages

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.painterResource
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
fun CartItemView(
    item : FoodItemDetails,
    index : Int,
    context: Context,
    navController: NavController,
    displayItemsViewModel: DisplayItemsViewModel
) {

    var noOfFoodItemsInCart by remember {
        mutableIntStateOf(1)
    }


    Card(
        modifier = Modifier
            .size(355.dp, 190.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.whitesmoke)
        ),
        border = BorderStroke(2.dp, colorResource(id = R.color.green3))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        )  {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(155.dp)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(item.foodItemImageUrl)
                            .crossfade(true)
                            .build(),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(150.dp, 125.dp)
                            .clip(RoundedCornerShape(30.dp))
                    )

                }
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp)
                ) {
                    item.foodItemName?.let {
                        Text(
                            text = it,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.darkbg),
                            textDecoration = TextDecoration.Underline
                        )
                    }

                    item.foodItemHotelName?.let {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.darkbg)
                        )
                    }

                    item.foodItemHotelLocation?.let {
                        Text(
                            text = it,
                            color = colorResource(id = R.color.darkbg)
                        )
                    }

                    Text(
                        text = "₹${item.foodItemPrice}",
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.green4),
                        fontSize = 18.sp
                    )

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        IconButton(
                            onClick = {
                                noOfFoodItemsInCart += 1
                                displayItemsViewModel.totalNoOfItemsInCartArr.value[index]++
                                displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue += item.foodItemPrice!!
                                displayItemsViewModel.totalNoOfItemsInCart.intValue += 1
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = colorResource(id = R.color.green3),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }

                        Text(
                            text = displayItemsViewModel.totalNoOfItemsInCartArr.value[index].toString(),
                            color = colorResource(id = R.color.green4),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )

                        IconButton(
                            onClick = {
                                if(noOfFoodItemsInCart > 0){
                                    noOfFoodItemsInCart -= 1
                                    displayItemsViewModel.totalNoOfItemsInCartArr.value[index]--
//                                    displayItemsViewModel.totalFoodFeeAmountInCartArr.value[index]
                                    displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue -= item.foodItemPrice!!
                                    displayItemsViewModel.totalNoOfItemsInCart.intValue -= 1
                                }
                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_remove_circle),
                                contentDescription = null,
                                tint = colorResource(id = R.color.green3),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                        }


                    }

                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.green3)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(37.dp),
                    onClick = {
                        //TODO remove the element from the file Storage

                        displayItemsViewModel.totalNoOfItemsInCartArr.value[index] = 1

                        item.foodItemName?.let {
                            item.foodItemHotelName?.let { it1 ->
                                FileStorage.deleteFoodItem(
                                    context = context,
                                    foodItemName = it,
                                    foodItemHotelName = it1
                                )
                            }
                        }

                        navController.navigate(Screen.UserBottomScreen.UserCart.bRoute)
                        navController.navigateUp()
                    }
                ) {
                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                        Text(
                            text = "Delete",
                            color = colorResource(id = R.color.whitesmoke),
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(12.dp))

}

//₹