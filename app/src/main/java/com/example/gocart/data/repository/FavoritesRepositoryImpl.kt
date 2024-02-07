package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.AddAndRemoveProductDto
import com.example.gocart.data.remote.dto.FavoritesDto
import com.example.gocart.domain.repositories.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor (private val apiServices: ApiServices) : FavoritesRepository {
    override suspend fun getFavoritesList(authorization: String) : FavoritesDto {
        return apiServices.getFavoritesList(authorization)
    }

    override suspend fun addOrRemoveProductToFavouritesList(authorization: String, productId: Int) : AddAndRemoveProductDto {
        return apiServices.addOrRemoveProductToFavouritesList(authorization, productId)
    }

}