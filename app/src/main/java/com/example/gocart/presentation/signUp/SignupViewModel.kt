package com.example.gocart.presentation.signUp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.ApiResponse
import com.example.gocart.domain.use_case.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val signUpUseCase : SignUpUseCase): ViewModel() {

    var state = MutableStateFlow(SignUpUIState())
        private set

    fun signUp (context: Context, email: String , password: String , name: String , phoneNumber: String){
        viewModelScope.launch(Dispatchers.IO) {
            signUpUseCase(context, email , password, name, phoneNumber).collect{ response ->
                withContext(Dispatchers.Main) {
                    when(response) {
                        is ApiResponse.Success -> state.value = SignUpUIState(data = response.data)
                        is ApiResponse.Error -> state.value = SignUpUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> state.value = SignUpUIState(isLoading = true)
                    }
                }
            }
        }
    }
}