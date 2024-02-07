package com.example.gocart.data.remote.dto


import com.google.gson.annotations.SerializedName

data class HomeDto(
    val status: Boolean,
    val message: String,
    val data: HomeProductsList
)
data class HomeProductsList (
    @SerializedName("products")
    val products: List<Product>
)
