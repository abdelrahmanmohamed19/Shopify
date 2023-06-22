package com.example.gocart.model.data

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: CData?
    )
data class CData(

    @SerializedName("current_page") val currentPage : Int,
    @SerializedName("data") val Data: List<CategoriesData>? = null
)

data class CategoriesData(
@SerializedName("id") val id: Int,
@SerializedName("image") val image: String,
@SerializedName("name") val name: String,
@SerializedName("price") val NewPrice : Double,
@SerializedName("old_price") val OldPrice : Double,
@SerializedName("discount") val discount : Double,
@SerializedName("description") val description: String,
@SerializedName(  "in_favorites") val favorites: Boolean,
@SerializedName("in_cart") val cart: Boolean,


)



