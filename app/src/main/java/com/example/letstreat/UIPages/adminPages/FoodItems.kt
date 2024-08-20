package com.example.letstreat.UIPages.adminPages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun FoodItems(
    item: FoodItemDetails,
    navController: NavController
) {
    Spacer(modifier = Modifier.height(16.dp))

    val itemIsVeg = if(item.foodItemIsVeg == true) "Veg" else "Non-Veg"

    Card(
        modifier = Modifier
            .size(350.dp, 170.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.whitesmoke)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(135.dp)
                    .padding(8.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(item.foodItemImageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(150.dp, 150.dp)
                        .clip(RoundedCornerShape(30.dp))
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    item.foodItemName?.let {
                        Text(
                            text = it,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(id = R.color.darkbg),
                            textDecoration = TextDecoration.Underline
                        )
                    }
                    Text(
                        text = "Price: ₹${item.foodItemPrice}",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.darkbg)
                    )
                    Text(
                        text =  "category : $itemIsVeg",
                        color = colorResource(id = R.color.darkbg)
                    )
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
                        // TODO: Navigate to edit page
                        navController.navigate(Screen.AdminFoodItemEditPage.route)
                    }
                ) {
                    Text(
                        text = "Edit",
                        color = colorResource(id = R.color.whitesmoke),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}
