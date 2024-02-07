package com.example.gocart.presentation.common

import com.example.gocart.data.remote.dto.CategoryProductsDto

data class CategoryProductsUIState(
    val data: CategoryProductsDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)
