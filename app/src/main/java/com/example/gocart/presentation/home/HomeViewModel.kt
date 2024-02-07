package com.example.gocart.presentation.home

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.common.ApiResponse
import com.example.gocart.common.Utils
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.domain.use_case.GetHomeProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase : GetHomeProductsUseCase) : ViewModel() {

    private var _homeProductsData = MutableStateFlow(emptyList<ProductItems>())
    val homeProductsData get() = _homeProductsData

    var isLoading = MutableStateFlow(false)
    var isSuccess = MutableStateFlow(false)

    fun getHomeProducts (context: Context, view: View) {
        useCase().onEach {
            when (it) {
                is ApiResponse.Success -> {
                    useCase.homeProductData.collect{
                        _homeProductsData.value = it
                        isLoading.value = false
                        isSuccess.value = true
                    }
                }
                is ApiResponse.Error -> {
                    isLoading.value = false
                    isSuccess.value = false
                    Utils.showSnackbar(view, it.message, { getHomeProducts(context,view) }, context)
                }
                is ApiResponse.Loading -> isLoading.value = true
            }
        }.launchIn(viewModelScope)
    }
}