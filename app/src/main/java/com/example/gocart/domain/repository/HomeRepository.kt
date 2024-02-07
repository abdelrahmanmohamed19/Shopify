package com.example.gocart.domain.repository

import com.example.gocart.data.remote.dto.HomeDto

interface HomeRepository {

    suspend fun getHomeData (token: String) : HomeDto
}