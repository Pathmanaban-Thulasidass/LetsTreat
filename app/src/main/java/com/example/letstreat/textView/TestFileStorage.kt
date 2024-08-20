package com.example.letstreat.textView

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.letstreat.datum.FoodItemDetails
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File


@Composable
fun CRUDFileStorage() {
    val context = LocalContext.current
    var foodItems by remember { mutableStateOf(loadFoodItemList(context)) }
    var foodItemNameToDelete by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Add Button
        Button(onClick = {
            val newItem = FoodItemDetails(
                "Pizza",
                10.0,
                "Hotel B",
                "Location A",
                true,
                "url1",
                "Category A"
            )
            addFoodItem(context, newItem)
            foodItems = loadFoodItemList(context)
        }) {
            Text(text = "Add Food Item")
        }

        // Update Button
        Button(onClick = {
            val updatedItem = FoodItemDetails(
                "VegPizza",
                12.0,
                "Hotel A",
                "Location A",
                true,
                "newUrl",
                "Category A"
            )
            updateFoodItem(context, updatedItem)
            foodItems = loadFoodItemList(context)
        }) {
            Text(text = "Update Food Item")
        }

        // Delete Button
        Button(onClick = {
            deleteFoodItem(context, "Pizza", "Hotel B")
            foodItems = loadFoodItemList(context)
        }) {
            Text(text = "Delete Food Item")
        }


        // Text field to input the name of the item to delete
        androidx.compose.material3.TextField(
            value = foodItemNameToDelete,
            onValueChange = { foodItemNameToDelete = it },
            label = { Text("Enter Food Item Name to Delete") },
            modifier = Modifier.fillMaxWidth()
        )

        // Load Button (to display the current list)
        Button(onClick = {
            foodItems = loadFoodItemList(context)
        }) {
            Text(text = "Load Food Items")
        }

        // Display the list of food items
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(foodItems) { item ->
                Text(text = item.toString())
            }
        }
    }
}



// Create
fun addFoodItem(context: Context, newItem: FoodItemDetails) {
    val foodItemList = loadFoodItemList(context).toMutableList()
    foodItemList.add(newItem)
    saveFoodItemList(context, foodItemList)
}

// Read
fun loadFoodItemList(context: Context): List<FoodItemDetails> {
    val file = File(context.filesDir, "food_items.json")
    return if (file.exists()) {
        val jsonString = file.readText()
        Json.decodeFromString(jsonString)
    } else {
        emptyList()
    }
}

// Update
fun updateFoodItem(context: Context, updatedItem: FoodItemDetails) {
    val foodItemList = loadFoodItemList(context).toMutableList()
    val index = foodItemList.indexOfFirst { it.foodItemName == updatedItem.foodItemName }
    if (index != -1) {
        foodItemList[index] = updatedItem
        saveFoodItemList(context, foodItemList)
    }
}

// Delete
fun deleteFoodItem(context: Context, foodItemName: String, foodItemHotelName: String) {
    val foodItemList = loadFoodItemList(context).toMutableList()
    foodItemList.removeAll { it.foodItemName == foodItemName && it.foodItemHotelName == foodItemHotelName }
    saveFoodItemList(context, foodItemList)
}


// Helper Function to Save the List
fun saveFoodItemList(context: Context, foodItemList: List<FoodItemDetails>) {
    val jsonString = Json.encodeToString(foodItemList)
    val file = File(context.filesDir, "food_items.json")
    file.writeText(jsonString)
}