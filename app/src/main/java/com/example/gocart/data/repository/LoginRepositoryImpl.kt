package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.LoginDto
import com.example.gocart.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : LoginRepository {

    override suspend fun login(email: String, password: String): LoginDto {
        return apiServices.login(email, password)
    }
}