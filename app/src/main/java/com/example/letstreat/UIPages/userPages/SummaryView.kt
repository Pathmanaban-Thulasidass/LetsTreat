package com.example.letstreat.UIPages.userPages

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.navigation.Screen
import com.example.letstreat.objects.FileStorage
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryView(
    authViewModel: AuthViewModel,
    displayItemsViewModel: DisplayItemsViewModel,
    navController: NavController
) {

    var deliveryLocation by remember {
        mutableStateOf("")
    }

    var isTextFiledEnabled by remember {
        mutableStateOf(false)
    }

    var foodItemsInCart by remember {
        mutableStateOf(emptyList<FoodItemDetails>())
    }

    foodItemsInCart = FileStorage.loadFoodItemList(LocalContext.current).toList()

    deliveryLocation = authViewModel.retrievedCurrentUserDetail.value?.userLocation.toString()

    var totalNoOfItemsInCartArr by remember {
        mutableStateOf(emptyList<Int>())
    }

    totalNoOfItemsInCartArr = displayItemsViewModel.totalNoOfItemsInCartArr.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.darkbg))
            .padding(16.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Text(
            text = "Delivery to",
            fontSize = 20.sp,
            color = colorResource(id = R.color.whitesmoke)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            OutlinedTextField(
                value = deliveryLocation,
                onValueChange ={
                    deliveryLocation = it
                },
                enabled = isTextFiledEnabled,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = colorResource(id = R.color.green3)
                ),
                modifier = Modifier
                    .weight(1f)
            )

            IconButton(
                onClick = {
                    isTextFiledEnabled = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = colorResource(id = R.color.green3)
                )
            }

        }
        
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "FoodItems :",
            textDecoration = TextDecoration.Underline,
            fontSize = 20.sp
        )
        
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            TableHeader(text = "Foodname")
            TableHeader(text = "Qty")
            TableHeader(text = "Price")
        }

        Divider(
            color = colorResource(id = R.color.green3)
        )

        foodItemsInCart.forEachIndexed {
            index,foodItem ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                TableCell(text = foodItem.foodItemName.toString())
                TableCell(text = totalNoOfItemsInCartArr[index].toString())
                TableCell(text = "₹" + "%.2f".format(foodItem.foodItemPrice?.times(totalNoOfItemsInCartArr[index])))
            }
            
            Divider(
                color = colorResource(id = R.color.green3)
            )

        }
        
        Spacer(modifier = Modifier.height(12.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            horizontalArrangement = Arrangement.End
        ){
            Text(
                text = "Total : ₹" + "%.2f".format(displayItemsViewModel.totalFoodFeeAmountInCart.doubleValue),
                color = colorResource(id = R.color.green3),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Box(modifier = Modifier
            .padding(bottom = 16.dp)
            .weight(1f),
            contentAlignment = Alignment.BottomCenter
        ){
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                onClick = {
                    navController.navigate(Screen.OrderSuccessView.route)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green3)
                ),
                border = BorderStroke(1.dp, colorResource(id = R.color.green4))
            ) {
                Text(
                    text = "Confirm Order",
                    color = colorResource(id = R.color.whitesmoke),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }

        }


    }

}

@Composable
fun TableHeader(
    text: String
) {
    Text(
        text = text,
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp)
            .background(colorResource(id = R.color.green3))
            .padding(8.dp),
        color = colorResource(id = R.color.whitesmoke),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun TableCell(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .width(120.dp)
            .padding(8.dp),
        color = colorResource(id = R.color.whitesmoke)
    )
}