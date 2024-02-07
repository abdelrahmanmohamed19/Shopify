package com.example.gocart.presentation.cart

import com.example.gocart.data.remote.dto.CartDto

data class CartUIState(
    val data: CartDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)
