package com.example.gocart.model.data


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("product")
    val product: Product? = null
)