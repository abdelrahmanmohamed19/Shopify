package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.HomeDto
import com.example.gocart.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : HomeRepository{

    override suspend fun getHomeData(token: String): HomeDto {
        return apiServices.getHomeData(token)
    }
}






