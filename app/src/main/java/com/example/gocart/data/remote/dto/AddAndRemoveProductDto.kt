package com.example.gocart.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AddAndRemoveProductDto (
    val status : Boolean,
    val message : String,
    val data: ProductData,
)
data class Data(
    val discount: Int? = null,
    val id: Int? = null,
    val image: String? = null,
    val quantity : Int = 0,
    @SerializedName("old_price")
    val oldPrice: Int? = null,
    val price: Int? = null
)