package com.example.gocart.domain.repository

import com.example.gocart.data.remote.dto.CategoryProductsDto

interface CategoryProductsRepository {

    suspend fun getCategoryProducts(token: String, categoryId : Int): CategoryProductsDto
}