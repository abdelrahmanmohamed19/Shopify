package com.example.gocart.presentation.favorites

import com.example.gocart.data.remote.dto.FavoritesDto

data class FavoritesUIState(
    val data: FavoritesDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)
