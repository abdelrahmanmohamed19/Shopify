package com.example.gocart.domain.repositories

import com.example.gocart.data.remote.dto.SignUpDto
import retrofit2.Response

interface SignUpRepository {

    suspend fun signUp(email : String, password : String, name : String, phone : String) : SignUpDto
}