package com.example.gocart.data.remote.dto

data class CategoriesProductsDto(
    val status : Boolean,
    val message : String,
    val data : CategoriesProductsData
)

data class CategoriesProductsData (
    val data : List<CategoriesProducts>
)