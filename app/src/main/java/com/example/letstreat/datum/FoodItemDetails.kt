package com.example.letstreat.datum

import kotlinx.serialization.Serializable

@Serializable
data class FoodItemDetails(
    val foodItemName: String? = null,
    val foodItemPrice: Double? = null,
    val foodItemHotelName: String? = null,
    val foodItemHotelLocation : String? = null,
    val foodItemIsVeg: Boolean? = null,
    val foodItemImageUrl: String? = null,
    val foodItemCategory : String? = null,
    var noOfFoodItemsInCart : Int? = 1,
    var foodHotelContactNo : String? = null
){
    constructor() : this(null, null, null, null, null,null,null,null,null)
}
