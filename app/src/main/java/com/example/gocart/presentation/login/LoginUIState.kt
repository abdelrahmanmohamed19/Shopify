package com.example.gocart.presentation.login

import com.example.gocart.data.remote.dto.LoginDto

data class LoginUIState (
    val data: LoginDto? = null,
    val errorMessage: String? = "",
    val isLoading: Boolean? = false
)