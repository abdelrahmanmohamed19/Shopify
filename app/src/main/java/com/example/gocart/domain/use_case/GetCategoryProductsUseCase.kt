package com.example.gocart.domain.use_case

import com.example.gocart.data.ApiResponse
import com.example.gocart.data.remote.dto.CategoryProductsDto
import com.example.gocart.domain.repository.CategoryProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCategoryProductsUseCase @Inject constructor(private val categoryProductsRepository: CategoryProductsRepository) {

   suspend operator fun invoke (token: String, categoryId : Int) : Flow<ApiResponse<CategoryProductsDto>> = flow {
        try {
            val data = categoryProductsRepository.getCategoryProducts(token, categoryId)
            emit(ApiResponse.Success(data = data))
        } catch (e : HttpException) {
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }.onStart {
       emit(ApiResponse.Loading())
   }
}