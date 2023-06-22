package com.example.gocart.model.data


import com.google.gson.annotations.SerializedName

data class buy(
    @SerializedName("data")
    val `data`: DataXx? = DataXx(),
    @SerializedName("message")
    val message: String? = "",
    @SerializedName("status")
    val status: Boolean? = false
)
data class DataXx(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("product")
    val product: Producttt? = null,
    @SerializedName("quantity")
    val quantity: Int? = null
)
data class Producttt(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("old_price")
    val oldPrice: Double? = null,
    @SerializedName("price")
    val price: Double? = null
)