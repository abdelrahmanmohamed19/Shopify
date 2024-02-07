package com.example.gocart.domain.repository

import com.example.gocart.data.remote.dto.SignUpDto

interface SignUpRepository {

    suspend fun signUp(
        email : String,
        password : String,
        name : String,
        phone : String
    ): SignUpDto
}