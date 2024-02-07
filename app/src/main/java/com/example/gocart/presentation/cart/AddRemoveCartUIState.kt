package com.example.gocart.presentation.cart

import com.example.gocart.data.remote.dto.AddRemoveProductDto

data class AddRemoveCartUIState(
    val data: AddRemoveProductDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)
