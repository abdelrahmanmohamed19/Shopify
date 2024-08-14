package com.example.gocart.domain.use_case

import android.content.Context
import com.example.gocart.common.ApiResponse
import com.example.gocart.data.local.SharedPreferences
import com.example.gocart.domain.repositories.SignInRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val signInRepository: SignInRepository) {

    suspend operator fun invoke (context: Context, email : String, password : String) : Flow<ApiResponse> = flow {
        try {
            val data = signInRepository.singIn(email, password)
            val sharedPreferences = SharedPreferences(context)
            sharedPreferences.apply {
                clearToken()
                saveToken(data.data.token)
                saveLoginStatus(1)
            }
            emit(ApiResponse.Success(data.message))
        } catch (e : HttpException) {
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}