package com.example.gocart.domain.repositories

import com.example.gocart.data.remote.dto.HomeDto
import retrofit2.Response

interface HomeRepository {

    suspend fun getHomeData () : HomeDto
}