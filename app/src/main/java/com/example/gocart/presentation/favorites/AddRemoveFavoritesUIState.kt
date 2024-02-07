package com.example.gocart.presentation.favorites

import com.example.gocart.data.remote.dto.AddRemoveProductDto

data class AddRemoveFavoritesUIState(
    val data: AddRemoveProductDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)
