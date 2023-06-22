package com.example.gocart.model.data

import android.text.BoringLayout

data class Products(
    val newPrice : String,
    val oldPrice : String,
    val image : String,
    val name : String,
    val discount : String,
    val favorites : Boolean,
    val cart : Boolean,
    val id : String
)
