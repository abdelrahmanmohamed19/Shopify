package com.example.gocart.domain.repositories

import com.example.gocart.data.remote.dto.AddAndRemoveProductDto
import com.example.gocart.data.remote.dto.CartDto
import retrofit2.Response

interface CartRepository {

    suspend fun getCartList (authorization : String) : CartDto

    suspend fun addOrRemoveProductToCartList (authorization : String, productId : Int) : AddAndRemoveProductDto
}