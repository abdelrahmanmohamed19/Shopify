package com.example.gocart.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.model.repositories.LogininRepository
import com.example.gocart.model.repositories.SignupRepository
import kotlinx.coroutines.launch

class SignupViewModel : ViewModel() {

    fun signUp (context: Context, email : String , password : String , name : String , phoneNumber : String ) : LiveData<Boolean>{
         return  SignupRepository(context).signup(email, password, name, phoneNumber)
        }


}