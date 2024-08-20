package com.example.letstreat.objects

import android.content.Context
import com.example.letstreat.datum.FoodItemDetails
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object FileStorage {

    // Create
    fun addFoodItem(context: Context, newItem: FoodItemDetails) {
        val foodItemList = loadFoodItemList(context).toMutableList()
        foodItemList.add(newItem)
        saveFoodItemList(context, foodItemList)
    }

    // Read
//    fun loadFoodItemList(context: Context): List<FoodItemDetails> {
//        val file = File(context.filesDir, "cart_food_items.json")
//        return if (file.exists()) {
//            val jsonString = file.readText()
//            Json.decodeFromString(jsonString)
//        } else {
//            emptyList()
//        }
//    }

    fun loadFoodItemList(context: Context): Set<FoodItemDetails> {
        val file = File(context.filesDir, "cart_food_items.json")
        return if (file.exists()) {
            val jsonString = file.readText()
            Json.decodeFromString<List<FoodItemDetails>>(jsonString).toSet()
        } else {
            emptySet()
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
    private fun saveFoodItemList(context: Context, foodItemList: List<FoodItemDetails>) {
        val jsonString = Json.encodeToString(foodItemList)
        val file = File(context.filesDir, "cart_food_items.json")
        file.writeText(jsonString)
    }
}