package com.example.gocart.presentation.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.ApiResponse
import com.example.gocart.domain.use_case.AddRemoveFromCartUseCase
import com.example.gocart.domain.use_case.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartUseCase: GetCartUseCase,
    private val addRemoveFromCartUseCase: AddRemoveFromCartUseCase
): ViewModel() {

    var state = MutableStateFlow(CartUIState())
        private set

    var addRemoveState = MutableStateFlow(AddRemoveCartUIState())
        private set

    fun getCartList(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getCartUseCase(token).collect{ response ->
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> state.value = CartUIState(data = response.data)
                        is ApiResponse.Error -> state.value = CartUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> state.value = CartUIState(isLoading = true)
                    }
                }
            }
        }
    }

    fun addRemoveFromCart(token: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            addRemoveFromCartUseCase(token,id).collect{ response ->
                withContext(Dispatchers.Main) {
                    when (response) {
                        is ApiResponse.Success -> {
                            addRemoveState.value = AddRemoveCartUIState(data = response.data)
                            getCartList(token)
                        }
                        is ApiResponse.Error -> addRemoveState.value = AddRemoveCartUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> addRemoveState.value = AddRemoveCartUIState(isLoading = true)
                    }
                }
            }
        }
    }
}
