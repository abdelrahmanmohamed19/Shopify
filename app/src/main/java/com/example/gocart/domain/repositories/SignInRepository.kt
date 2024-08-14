package com.example.gocart.domain.repositories

import com.example.gocart.data.remote.dto.LoginDto
import retrofit2.Response

interface SignInRepository {

    suspend fun singIn(email : String , password : String  ) : LoginDto
}