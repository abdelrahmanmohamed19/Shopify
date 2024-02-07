package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.AddRemoveProductDto
import com.example.gocart.data.remote.dto.CartDto
import com.example.gocart.domain.repository.CartRepository
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : CartRepository {

    override suspend fun getCartList(authorization: String): CartDto {
        return apiServices.getCartList(authorization)
    }

    override suspend fun addRemoveProductFromCartList(
        authorization: String,
        productId: Int
    ): AddRemoveProductDto {
        return apiServices.addRemoveProductsFromCartList(authorization, productId)
    }

}