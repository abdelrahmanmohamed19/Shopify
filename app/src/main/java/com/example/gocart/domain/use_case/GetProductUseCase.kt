package com.example.gocart.domain.use_case

import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.domain.repositories.ProductsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val productsRepository: ProductsRepository) {

    private var _productsList = MutableStateFlow(emptyList<ProductItems>())
    val productsList get() = _productsList

    operator fun invoke (categoryId : Int) : Flow<ApiResponse> = flow {
        try {
            emit(ApiResponse.Loading(""))
            val data = productsRepository.getCategoriesProducts(categoryId)
            data.data.data.forEach {
                _productsList.value = _productsList.value + ProductItems (
                    newPrice = it.price.toString(),
                    oldPrice = it.old_price.toString(),
                    image = it.image,
                    name = it.name,
                    id = it.id.toString()
                )
            }
            emit(ApiResponse.Success(""))
        } catch (e : HttpException) {
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}