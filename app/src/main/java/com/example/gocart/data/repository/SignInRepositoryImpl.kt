package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.LoginDto
import com.example.gocart.domain.repositories.SignInRepository
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : SignInRepository {
    override suspend fun singIn(email: String, password: String) : LoginDto {
        return apiServices.signIn(email, password)
    }
}