package com.example.gocart.domain.use_case

import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.repositories.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AddOrRemoveToFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    operator fun invoke (token : String, id : Int) : Flow<ApiResponse> = flow {
        try {
            emit(ApiResponse.Loading(""))
            val data = favoritesRepository.addOrRemoveProductToFavouritesList(token, id)
            emit(ApiResponse.Success(data.message))
        } catch (e : HttpException) {
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}