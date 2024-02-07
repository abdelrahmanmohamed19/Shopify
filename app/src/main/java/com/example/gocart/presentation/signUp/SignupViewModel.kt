package com.example.gocart.presentation.signUp

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.gocart.R
import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.use_case.SignUpUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(private val useCase : SignUpUseCase) : ViewModel() {

    fun signUp (context: Context, email : String , password : String , name : String , phoneNumber : String , navController: NavController) {
        viewModelScope.launch(Dispatchers.IO) {
            val apiResult = useCase(context, email , password, name, phoneNumber)
            withContext(Dispatchers.Main) {
                apiResult.collect{
                    when(it) {
                        is ApiResponse.Success -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                            navController.navigate(R.id.action_signUpFragment_to_displayFragment)
                        }
                        is ApiResponse.Error -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        is ApiResponse.Loading -> {}
                    }
                }
            }
        }
    }
}