package com.example.gocart.domain.repository

import com.example.gocart.data.remote.dto.AddRemoveProductDto
import com.example.gocart.data.remote.dto.CartDto

interface CartRepository {

    suspend fun getCartList (authorization : String) : CartDto

    suspend fun addRemoveProductFromCartList (authorization : String, productId : Int) : AddRemoveProductDto
}