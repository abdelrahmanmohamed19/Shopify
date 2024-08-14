package com.example.gocart.domain.repositories

import com.example.gocart.data.remote.dto.AddAndRemoveProductDto
import com.example.gocart.data.remote.dto.FavoritesDto
import retrofit2.Response

interface FavoritesRepository {

    suspend fun getFavoritesList (authorization : String) : FavoritesDto

    suspend fun addOrRemoveProductToFavouritesList (authorization : String, productId : Int) : AddAndRemoveProductDto
}