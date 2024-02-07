package com.example.gocart.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CartDto(
    val status : Boolean,
    val message : String,
    val data : CartItems
)

data class CartItems(
    @SerializedName("cart_items")
    val cartItems: List<CartItem>,
    @SerializedName("sub_total")
    val subTotal: Double? = null,
    @SerializedName("total")
    val total: Double? = null
)

data class CartItem(
    val product: Product,
    val quantity: Int
)