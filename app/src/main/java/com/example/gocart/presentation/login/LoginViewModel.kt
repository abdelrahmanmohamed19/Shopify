package com.example.gocart.presentation.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.ApiResponse
import com.example.gocart.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {

    var state = MutableStateFlow(LoginUIState())
        private set

    fun login (email : String, password : String, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase(context,email, password).collect{ response ->
                withContext(Dispatchers.Main) {
                    when(response) {
                        is ApiResponse.Success -> state.value = LoginUIState(data = response.data)
                        is ApiResponse.Error -> state.value = LoginUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> state.value = LoginUIState(isLoading = true)
                    }
                }
            }

        }
    }
}
