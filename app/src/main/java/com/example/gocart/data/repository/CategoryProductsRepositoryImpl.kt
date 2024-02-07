package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.CategoryProductsDto
import com.example.gocart.domain.repository.CategoryProductsRepository
import javax.inject.Inject

class CategoryProductsRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : CategoryProductsRepository{

    override suspend fun getCategoryProducts(token: String, categoryId: Int): CategoryProductsDto {
        return apiServices.getCategoryProducts(token, categoryId)
    }
}