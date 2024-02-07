package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.AddRemoveProductDto
import com.example.gocart.data.remote.dto.FavoritesDto
import com.example.gocart.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor (private val apiServices: ApiServices) : FavoritesRepository {

    override suspend fun getFavoritesList(authorization: String) : FavoritesDto {
        return apiServices.getFavoritesList(authorization)
    }

    override suspend fun addRemoveProductFromFavoritesList(authorization: String, productId: Int): AddRemoveProductDto {
        return apiServices.addRemoveProductFromFavoritesList(authorization, productId)
    }

}