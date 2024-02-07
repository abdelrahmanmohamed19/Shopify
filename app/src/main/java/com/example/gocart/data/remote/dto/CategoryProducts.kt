package com.example.gocart.data.remote.dto

data class CategoryProducts (
    val id : Int,
    val price : Double,
    val oldPrice : Double,
    val image : String,
    val name : String,
    val description: String = "",
    val in_favorites: Boolean,
    val in_cart: Boolean
)
