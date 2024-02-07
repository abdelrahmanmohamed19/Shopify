package com.example.gocart.domain.use_case

import android.content.Context
import com.example.gocart.data.ApiResponse
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.data.remote.dto.LoginDto
import com.example.gocart.domain.repository.LoginRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke (context: Context, email : String, password : String) : Flow<ApiResponse<LoginDto>> = flow {
        try {
            val data = loginRepository.login(email, password)
            AppPreferences(context).apply {
                saveToken(data.data.token)
                saveLoginStatus(true)
            }
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