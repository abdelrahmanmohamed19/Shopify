package com.example.gocart.domain.use_case

import android.content.Context
import com.example.gocart.data.ApiResponse
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.data.remote.dto.SignUpDto
import com.example.gocart.domain.repository.SignUpRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val signUpRepository: SignUpRepository) {

    suspend operator fun invoke(context : Context, email : String, password : String, name : String, phone : String) : Flow<ApiResponse<SignUpDto>> = flow {
       try {
           val data = signUpRepository.signUp(email, password, name, phone)
           AppPreferences(context).apply {
               saveToken(data.data?.token ?: "")
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