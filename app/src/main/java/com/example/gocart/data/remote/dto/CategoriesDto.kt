package com.example.gocart.data.remote.dto

data class CategoriesResponse(
    val status: Boolean,
    val message: String,
    val data: CategoryCardDataList
)
data class CategoryCardDataList(
    val data: List<CategoryCardData>
)



