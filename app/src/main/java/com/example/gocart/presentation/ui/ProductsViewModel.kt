package com.example.gocart.presentation.ui

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.common.ApiResponse
import com.example.gocart.common.Utils
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.domain.use_case.GetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private var _productsList = MutableStateFlow(emptyList<ProductItems>())
    val productsList get() = _productsList

    var isLoading = MutableStateFlow(false)
    var isSuccess = MutableStateFlow(false)

    fun getCategoriesProducts (categoryId : Int, context : Context, view: View) {
        getProductUseCase(categoryId).onEach{
            when (it) {
                is ApiResponse.Success -> {
                    getProductUseCase.productsList.collect{
                        _productsList.value = it
                        isLoading.value = false
                        isSuccess.value = true
                    }
                }
                is ApiResponse.Error -> {
                    isLoading.value = false
                    isSuccess.value = false
                    Utils.showSnackbar(view, it.message, { getCategoriesProducts(categoryId,context,view) }, context)
                }
                is ApiResponse.Loading -> {
                    isLoading.value = true
                }
            }
        }.launchIn(viewModelScope)
    }
}