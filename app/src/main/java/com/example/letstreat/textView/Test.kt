//package com.example.letstreat.textView
//
//import android.util.Log
//import android.widget.Toast
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.example.letstreat.datum.FoodItemDetails
//import com.google.firebase.database.*
//@Composable
//fun Test(
//) {
//    val context = LocalContext.current
//    var retrievedFoodItems by remember { mutableStateOf<List<FoodItemDetails>>(emptyList()) }
//    var loading by remember { mutableStateOf(false) }
//    var errorMessage by remember { mutableStateOf<String?>(null) }
//
//    fun retrieveAllAdminFoodChildElements(
//        parentPath: String,
//        onSuccess: (List<FoodItemDetails>) -> Unit,
//        onFailure: (DatabaseError) -> Unit
//    ) {
//        val databaseRef = FirebaseDatabase.getInstance().getReference(parentPath)
//
//        databaseRef.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val items = mutableListOf<FoodItemDetails>()
//                if (snapshot.exists()) {
//                    for (childSnapshot in snapshot.children) {
//                        val item = childSnapshot.getValue(FoodItemDetails::class.java)
//                        if (item != null) {
//                            items.add(item)
//                        } else {
//                            Log.w("Firebase", "Null item at ${childSnapshot.key}")
//                        }
//                    }
//                    onSuccess(items)
//                } else {
//                    Log.d("Firebase", "No data available at $parentPath")
//                    onSuccess(emptyList())
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Log.e("Firebase", "Database error: ${error.message}")
//                onFailure(error)
//            }
//        })
//    }
//
//
//    Column(
//        modifier = Modifier.padding(20.dp)
//    ) {
//        Button(
//            onClick = {
//                retrieveAllAdminFoodChildElements(
//                    "HotelFoodItemDetails/priya6898",
//                    { foodItemDetails ->
//                        retrievedFoodItems = foodItemDetails
//                    },
//                    { databaseError ->
//                        Toast.makeText(context, "Error: ${databaseError.message}", Toast.LENGTH_SHORT).show()
//                    }
//                )
//            }
//        ) {
//            Text(text = "Retrieve Data")
//        }
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        if (retrievedFoodItems.isEmpty()) {
//            Text(text = "No items found.")
//        } else {
//            retrievedFoodItems.forEach { item ->
//                Text(text = item.toString())
//                Spacer(modifier = Modifier.height(10.dp))
//            }
//        }
//    }
//}
