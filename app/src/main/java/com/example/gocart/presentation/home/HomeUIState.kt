package com.example.gocart.presentation.home

import com.example.gocart.data.remote.dto.HomeDto

data class HomeUIState (
    val data: HomeDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)