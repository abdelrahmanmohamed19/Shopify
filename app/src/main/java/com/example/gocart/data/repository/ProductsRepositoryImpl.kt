package com.example.gocart.data.repository

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.remote.dto.CategoriesProductsDto
import com.example.gocart.domain.repositories.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(private val apiServices: ApiServices) : ProductsRepository{
    override suspend fun getCategoriesProducts(categoryId: Int): CategoriesProductsDto {
        return apiServices.getCategoriesProducts(categoryId)
    }
}