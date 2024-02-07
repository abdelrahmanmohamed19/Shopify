package com.example.gocart.domain.use_case

import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    private var _favoritesList = MutableStateFlow(emptyList<ProductItems>())
    val favoritesList get() = _favoritesList

   operator fun invoke (token : String) : Flow<ApiResponse> = flow {
        try {
            emit(ApiResponse.Loading(""))
            val data = favoritesRepository.getFavoritesList(token)
            _favoritesList.value = data.data.data.map{
                ProductItems (
                    newPrice = it.product.price.toString(),
                    oldPrice = it.product.old_price.toString(),
                    image = it.product.image,
                    name = it.product.name,
                    id = it.product.id.toString(),
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