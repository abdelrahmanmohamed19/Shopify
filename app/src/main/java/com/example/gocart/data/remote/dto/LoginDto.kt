package com.example.gocart.data.remote.dto

data class LoginDto(
    val status: Boolean,
    val message: String,
    val data: User
)
