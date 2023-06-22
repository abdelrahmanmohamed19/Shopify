package com.example.gocart.model.data


import com.google.gson.annotations.SerializedName

data class Home(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Ddata?
)
data class Ddata(
    @SerializedName("products")
    val products: List<Product?>? = null,
)

data class Product(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("images")
    val images: List<String?>? = null,
    @SerializedName("in_cart")
    val inCart: Boolean? = null,
    @SerializedName("in_favorites")
    val inFavorites: Boolean? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("old_price")
    val oldPrice: Double? = null,
    @SerializedName("price")
    val price: Double? = null
)
