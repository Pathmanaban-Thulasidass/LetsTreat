//package com.example.letstreat.textView
//
//import android.util.Log
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.letstreat.datum.FoodItemDetails
//import com.google.firebase.database.FirebaseDatabase
//
//@Composable
//fun Test2() {
//    var food by remember { mutableStateOf<FoodItemDetails?>(null) }
//
//    fun fetchFoodItems(
//        path: String,
//        onSuccess: (FoodItemDetails) -> Unit,
//        onFailure: (Exception) -> Unit
//    ) {
//        val rootRef = FirebaseDatabase.getInstance().reference.child("HotelFoodItemDetails").child("priya6898")
//        val itemRef = rootRef.child(path)
//
//        Log.d("Firebase", "Fetching data from path: HotelFoodItemDetails/priya6898/$path")
//
//        itemRef.get()
//            .addOnSuccessListener { dataSnapshot ->
//                val temp = dataSnapshot.getValue(FoodItemDetails::class.java)
//                if (temp != null) {
//                    Log.d("Firebase", "Data successfully retrieved: $temp")
//                    onSuccess(temp)
//                } else {
//                    Log.e("Firebase", "Data retrieved is null")
//                    onFailure(Exception("Item is null"))
//                }
//            }
//            .addOnFailureListener { exception ->
//                Log.e("Firebase", "Error fetching data: ${exception.message}")
//                onFailure(exception)
//            }
//    }
//
//    Column(
//        modifier = Modifier.padding(32.dp)
//    ) {
//        Button(
//            onClick = {
//                fetchFoodItems("priyaPizza",
//                    onSuccess = {
//                        Log.d("Firebase", "Updating UI with fetched data")
//                        food = it
//                    },
//                    onFailure = { exception ->
//                        Log.e("Firebase", "Failed to fetch data: ${exception.message}")
//                    }
//                )
//            }
//        ) {
//            Text(text = "Murugaaa........")
//        }
//
//        food?.let {
//            Text(text = it.foodItemName ?: "No Name")
//            Text(text = it.foodItemPrice?.toString() ?: "No Price")
//            Text(text = it.foodItemHotelName ?: "No Hotel Name")
//        }
//    }
//}
//
