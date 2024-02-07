package com.example.gocart.domain.repositories

import com.example.gocart.data.remote.dto.CategoriesProductsDto
import retrofit2.Response

interface ProductsRepository {

    suspend fun getCategoriesProducts(categoryId : Int) : CategoriesProductsDto
}