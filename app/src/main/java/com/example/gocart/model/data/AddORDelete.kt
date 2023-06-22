package com.example.gocart.model.data


import com.google.gson.annotations.SerializedName

data class AddORDelete(
    @SerializedName("data")
    val `data`: da,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("status")
    val status: Boolean? = null
)
data class da(
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("old_price")
    val oldPrice: Int? = null,
    @SerializedName("price")
    val price: Int? = null
)