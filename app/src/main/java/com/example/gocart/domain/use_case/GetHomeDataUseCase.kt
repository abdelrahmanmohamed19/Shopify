package com.example.gocart.domain.use_case

import android.util.Log
import com.example.gocart.data.ApiResponse
import com.example.gocart.data.remote.dto.HomeDto
import com.example.gocart.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(private val homeRepository: HomeRepository) {

    suspend operator fun invoke (token: String) : Flow<ApiResponse<HomeDto>> = flow {
        try {
            val data = homeRepository.getHomeData(token)
            Log.i("mainTagdata", data.toString())
            emit(ApiResponse.Success(data = data))
        } catch (e : HttpException) {
            Log.i("mainTagdata", e.localizedMessage ?: "")
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            Log.i("mainTagdata", e.localizedMessage ?: "")
            emit(ApiResponse.Error("no internet connection"))
        }
    }.onStart {
        emit(ApiResponse.Loading())
    }
}