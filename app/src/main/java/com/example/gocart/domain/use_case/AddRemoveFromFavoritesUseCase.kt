package com.example.gocart.domain.use_case

import com.example.gocart.data.ApiResponse
import com.example.gocart.data.remote.dto.AddRemoveProductDto
import com.example.gocart.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddRemoveFromFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    operator fun invoke (token : String, id : Int) : Flow<ApiResponse<AddRemoveProductDto>> = flow {
        try {
            val data = favoritesRepository.addRemoveProductFromFavoritesList(token, id)
            emit(ApiResponse.Success(data  = data))
        } catch (e : HttpException) {
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }.onStart {
        emit(ApiResponse.Loading())
    }
}