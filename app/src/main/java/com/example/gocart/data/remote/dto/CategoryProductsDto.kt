package com.example.gocart.data.remote.dto

data class CategoryProductsDto(
    val status : Boolean,
    val message : String,
    val data : CategoryProductsData
)

data class CategoryProductsData (
    val data : List<CategoryProducts>
)