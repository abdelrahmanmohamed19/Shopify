package com.example.gocart.domain.repository

import com.example.gocart.data.remote.dto.AddRemoveProductDto
import com.example.gocart.data.remote.dto.FavoritesDto

interface FavoritesRepository {

    suspend fun getFavoritesList (authorization : String) : FavoritesDto

    suspend fun addRemoveProductFromFavoritesList (authorization : String, productId : Int): AddRemoveProductDto
}