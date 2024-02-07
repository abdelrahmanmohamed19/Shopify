package com.example.gocart.domain.use_case

import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeProductsUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    private var _homeProductData = MutableStateFlow(emptyList<ProductItems>())
    val homeProductData get() = _homeProductData

    operator fun invoke () : Flow<ApiResponse> = flow {
        try {
            emit(ApiResponse.Loading(""))
            val data = homeRepository.getHomeData()
            data.data.products.forEach {
                _homeProductData.value = _homeProductData.value + ProductItems (
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