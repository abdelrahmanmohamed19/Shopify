package com.example.gocart.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.ApiResponse
import com.example.gocart.domain.use_case.GetHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeDataUseCase : GetHomeDataUseCase) : ViewModel() {

    var state = MutableStateFlow(HomeUIState())
        private set

    fun getHomeProducts (token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getHomeDataUseCase(token).collect { response ->
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> state.value = HomeUIState(data = response.data)
                        is ApiResponse.Error -> state.value = HomeUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> state.value = HomeUIState(isLoading = true)
                    }
                }
            }
        }
    }
}
