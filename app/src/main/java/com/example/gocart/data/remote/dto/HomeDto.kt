package com.example.gocart.data.remote.dto


import com.google.gson.annotations.SerializedName

data class HomeDto(
    val status: Boolean,
    val message: String,
    val data: HomeData
)
data class HomeData (
    @SerializedName("products")
    val products: List<Product>,
    val banners: List<banner>
)
data class banner (
    val id: Int,
    val image: String
)
