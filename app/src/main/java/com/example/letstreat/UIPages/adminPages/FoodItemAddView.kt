package com.example.letstreat.UIPages.adminPages

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.letstreat.R
import com.example.letstreat.datum.FoodItemDetails
import com.example.letstreat.datum.ToggleItem
import com.example.letstreat.navigation.Screen
import com.example.letstreat.viewModels.AuthViewModel
import com.example.letstreat.viewModels.DisplayItemsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemAddView(
    authViewModel: AuthViewModel,
    displayItemsViewModel: DisplayItemsViewModel,
    navController : NavController
) {

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var foodItemName by remember {
        mutableStateOf("")
    }

    var foodItemPrice by remember {
        mutableStateOf("")
    }

    var foodItemCategory by remember {
        mutableStateOf("")
    }
    
    var dropdownExpanded by remember {
        mutableStateOf(false)
    }

    
    var foodItemUrl by remember {
        mutableStateOf("")
    }

    var foodItemIsVeg by remember {
        mutableStateOf(true)
    }

    val radioButtons = remember {
        mutableStateListOf(
            ToggleItem(
                isSelected = true,
                text = "Veg"
            ),
            ToggleItem(
                isSelected = false,
                text = "Non-Veg"
            )
        )
    }

    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = {
                uri ->
            selectedImageUri = uri
        }
    )

    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(selectedImageUri) {
        selectedImageUri?.let { uri ->
            coroutineScope.launch {
                try {
                    val fileName = authViewModel.retrievedCurrentHotelDetails.value!!.hotelName + uri.lastPathSegment
                    foodItemUrl = displayItemsViewModel.uploadImageToFirebase(uri, fileName)
                } catch (exception: Exception) {
                    Toast.makeText(
                        context,
                        "Upload failed: ${exception.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        //To fullFill the top portion edge to edge
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        )

        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(200.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.green1)
            )
        ){
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                selectedImageUri?.let { uri ->
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }

        Button(
            onClick = {
                launcher.launch("image/*")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green3)
            )
        ) {
            Text(
                "Select Image",
                color = colorResource(id = R.color.whitesmoke),
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = foodItemName,
            onValueChange = {
                foodItemName = it
            },
            label = {
                Text(text = "Enter this food name")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.green3),
                unfocusedLabelColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.green3),
                unfocusedBorderColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = foodItemPrice,
            onValueChange = {
                foodItemPrice = it
            },
            label = {
                Text(text = "Enter this food price")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.green3),
                unfocusedLabelColor = Color.White,
                focusedLabelColor = colorResource(id = R.color.green3),
                unfocusedBorderColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            OutlinedTextField(
                value = foodItemCategory,
                onValueChange = {},
                enabled = false,
                modifier = Modifier
                    .width(140.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    disabledTextColor = colorResource(id = R.color.whitesmoke),
                    disabledBorderColor = colorResource(id = R.color.green3)
                )
            )

            Spacer(modifier = Modifier.width(20.dp))

            Box {
                Button(
                    onClick = {
                        dropdownExpanded = true
                    }
                ) {
                    Text(text = "Select Category")
                    Icon(imageVector = Icons.Default.KeyboardArrowDown,contentDescription = null)
                }
                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = {
                       dropdownExpanded = false
                    }
                ) {
                    DropdownMenuItem(
                        text = {
                           Text(text = "Biriyani")
                        },
                        onClick = {
                            foodItemCategory = "Biriyani"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "South Indian")
                        },
                        onClick = {
                            foodItemCategory = "South Indian"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Chinese")
                        },
                        onClick = {
                            foodItemCategory = "Chinese"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Cake")
                        },
                        onClick = {
                            foodItemCategory = "Cake"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Dosa")
                        },
                        onClick = {
                            foodItemCategory = "Dosa"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Omellete")
                        },
                        onClick = {
                            foodItemCategory = "Omellete"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Desserts")
                        },
                        onClick = {
                            foodItemCategory = "Desserts"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "North Indian")
                        },
                        onClick = {
                            foodItemCategory = "North Indian"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Rolls")
                        },
                        onClick = {
                            foodItemCategory = "Rolls"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Gulab Jamun")
                        },
                        onClick = {
                            foodItemCategory = "Gulab Jamun"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Noodles")
                        },
                        onClick = {
                            foodItemCategory = "Noodles"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Kebabs")
                        },
                        onClick = {
                            foodItemCategory = "Kebabs"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Pokada")
                        },
                        onClick = {
                            foodItemCategory = "Pokada"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Burger")
                        },
                        onClick = {
                            foodItemCategory = "Burger"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Shawarma")
                        },
                        onClick = {
                            foodItemCategory = "Shawarma"
                            dropdownExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = {
                            Text(text = "Drinks")
                        },
                        onClick = {
                            foodItemCategory = "Drinks"
                            dropdownExpanded = false
                        }
                    )

                }

            }

        }

        Spacer(modifier = Modifier.height(16.dp))


        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Text(text = "Is this item Veg?")
            radioButtons.forEachIndexed { index, toggleItem ->

                RadioButton(
                    selected = toggleItem.isSelected,
                    onClick = {
                        radioButtons.replaceAll {
                            it.copy(
                                isSelected = it.text == toggleItem.text
                            )
                        }
                    },
                    colors = RadioButtonDefaults.colors(
                        colorResource(id = R.color.green3)
                    )
                )
                Text(
                    text = radioButtons[index].text,
                    modifier = Modifier
                        .clickable {
                            radioButtons.replaceAll {
                                it.copy(
                                    isSelected = it.text == toggleItem.text
                                )
                            }
                        }
                )

            }

        }





//        radioButtons.forEachIndexed { index, toggle ->
//            Row (
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier
//                    .clickable {
//                        radioButtons.replaceAll {
//                            it.copy(
//                                isSelected = it.text == toggle.text
//                            )
//                        }
//                    }
//                    .padding(end = 16.dp)
//            ){
//                Text(text = radioButtons[index].text)
//                RadioButton(
//                    selected = toggle.isSelected,
//                    onClick = {
//                        radioButtons.replaceAll {
//                            it.copy(
//                                isSelected = it.text == toggle.text
//                            )
//                        }
//                    },
//                    colors = RadioButtonDefaults.colors(
//                        Color.Blue
//                    )
//                )
//            }
//        }



        Spacer(modifier = Modifier.height(16.dp))


        Button(

            onClick = {
                //TODO upload Food Details

                foodItemIsVeg = radioButtons[0].isSelected

                val newFoodDetails = FoodItemDetails(
                    foodItemName = foodItemName,
                    foodItemPrice = foodItemPrice.toDouble(),
                    foodItemImageUrl = foodItemUrl,
                    foodItemHotelName = authViewModel.retrievedCurrentHotelDetails.value!!.hotelName,
                    foodItemHotelLocation = authViewModel.retrievedCurrentHotelDetails.value!!.hotelLocation,
                    foodItemIsVeg = foodItemIsVeg,
                    foodItemCategory = foodItemCategory,
                    foodHotelContactNo = authViewModel.retrievedCurrentHotelDetails.value!!.hotelContactNo
                )

                authViewModel.retrievedCurrentHotelDetails.value!!.hotelContactNo?.let {
                    displayItemsViewModel.addFoodItemDetails(
                        newFoodDetails,
                        it
                    ){
                        if(it){
                            navController.navigate(Screen.AdminBottomScreen.AdminHome.route)
                            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show()
                        }
                    }
                }


            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green3)
            ),
            enabled = foodItemName.isNotEmpty() && foodItemPrice.isNotEmpty() && selectedImageUri != null && foodItemCategory.isNotEmpty()
        ) {
            Text(
                text = "Add Item",
                color = colorResource(id = R.color.whitesmoke),
                fontSize = 20.sp
            )
        }

    }

}
