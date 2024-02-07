package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.AddAndRemoveProductDto
import com.example.gocart.data.remote.dto.CartDto
import com.example.gocart.domain.repositories.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : CartRepository {
    override suspend fun getCartList(authorization: String): CartDto {
        return apiServices.getCartList(authorization)
    }

    override suspend fun addOrRemoveProductToCartList(
        authorization: String,
        productId: Int
    ): AddAndRemoveProductDto {
        return apiServices.addOrRemoveProductToCartList(authorization, productId)
    }

}