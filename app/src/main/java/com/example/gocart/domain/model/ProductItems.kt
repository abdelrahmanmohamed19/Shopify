package com.example.gocart.domain.model

data class ProductItems(
    val id : String,
    val image : String,
    val name : String,
    val newPrice : String,
    val oldPrice : String,
    val description: String = "",
    var isFavorite: Boolean,
    val inCart: Boolean
)
