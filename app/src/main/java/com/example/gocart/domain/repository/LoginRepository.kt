package com.example.gocart.domain.repository

import com.example.gocart.data.remote.dto.LoginDto

interface LoginRepository {

    suspend fun login(email : String, password : String  ): LoginDto
}