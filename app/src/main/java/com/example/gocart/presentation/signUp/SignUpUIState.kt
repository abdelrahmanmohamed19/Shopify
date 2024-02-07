package com.example.gocart.presentation.signUp

import com.example.gocart.data.remote.dto.SignUpDto

data class SignUpUIState (
    val data: SignUpDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)