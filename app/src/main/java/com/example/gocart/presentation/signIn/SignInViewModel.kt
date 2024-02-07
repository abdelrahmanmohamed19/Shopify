package com.example.gocart.presentation.signIn

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.gocart.R
import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.use_case.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val useCase: SignInUseCase) : ViewModel() {

    fun signIn (email : String, password : String, navController: NavController , context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResult = useCase(context,email, password)
            withContext(Dispatchers.Main) {
                apiResult.collect{
                    when(it) {
                        is ApiResponse.Success -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_signInFragment_to_displayFragment)
                        }
                        is ApiResponse.Error -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        is ApiResponse.Loading -> {}
                    }
                }
            }
        }
    }
}
