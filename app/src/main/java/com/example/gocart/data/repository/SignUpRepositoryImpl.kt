package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.SignUpDto
import com.example.gocart.domain.repository.SignUpRepository
import javax.inject.Inject


class SignUpRepositoryImpl @Inject constructor(private val apiServices : ApiServices): SignUpRepository {

    override suspend fun signUp(
        email : String,
        password : String,
        name : String,
        phone : String
    ): SignUpDto {
        return apiServices.signUp(email, password, name, phone)
    }
}