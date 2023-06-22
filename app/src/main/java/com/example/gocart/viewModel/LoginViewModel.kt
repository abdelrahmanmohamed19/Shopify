package com.example.gocart.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gocart.model.SessionManager
import com.example.gocart.model.repositories.LogininRepository
import com.example.gocart.model.repositories.SignupRepository
import com.example.gocart.ui.registration.Login
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    fun login(context: Context, email: String, password: String): LiveData<Boolean> {
        val repo = LogininRepository(context)
        val manager = SessionManager(context)
        repo.user_token.observeForever { token -> _token.value = token
            manager.saveToken(token.toString())
        }
        return repo.login(email, password)
    }
}