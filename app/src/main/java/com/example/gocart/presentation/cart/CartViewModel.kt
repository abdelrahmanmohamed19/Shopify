package com.example.gocart.presentation.cart

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.common.ApiResponse
import com.example.gocart.common.Utils
import com.example.gocart.domain.model.CartItem
import com.example.gocart.domain.use_case.AddOrRemoveToCartUseCase
import com.example.gocart.domain.use_case.GetCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val getCartUseCase: GetCartUseCase, private val addOrRemoveToCartUseCase: AddOrRemoveToCartUseCase) : ViewModel() {

    private var _cartList = MutableStateFlow(emptyList<CartItem>())
    val cartList get() = _cartList

    private var _totalPrice = ""
    val totalPrice get() = _totalPrice

    var isLoading = MutableStateFlow(false)
    var isSuccess = MutableStateFlow(false)

    fun getCartList(token: String, context: Context, view:View) {
        getCartUseCase(token).onEach{
            when (it) {
                is ApiResponse.Success -> {
                    _totalPrice = getCartUseCase.totalPrice
                    getCartUseCase.cartList.collect { cartItemList ->
                        _cartList.value = cartItemList
                        isLoading.value = false
                        isSuccess.value = true
                    }
                }
                is ApiResponse.Error -> {
                    isLoading.value = false
                    isSuccess.value = false
                    Utils.showSnackbar(view, it.message, { getCartList(token,context,view) }, context)
                }
                is ApiResponse.Loading -> {
                    isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }

    fun addOrRemoveToCart(token: String, id: Int, context: Context) {
        addOrRemoveToCartUseCase(token, id).onEach{
            when (it) {
                is ApiResponse.Success -> {
                    val updatedList = _cartList.value.toMutableList()
                    updatedList.removeIf { it.id == id }
                    _cartList.value = updatedList
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Error -> Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                is ApiResponse.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}
