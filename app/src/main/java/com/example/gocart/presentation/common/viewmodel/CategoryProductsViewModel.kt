package com.example.gocart.presentation.common.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.ApiResponse
import com.example.gocart.domain.use_case.GetCategoryProductsUseCase
import com.example.gocart.presentation.common.CategoryProductsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryProductsViewModel @Inject constructor(private val getCategoryProductsUseCase: GetCategoryProductsUseCase): ViewModel() {

    var state = MutableStateFlow(CategoryProductsUIState())
        private set

    fun getCategoriesProducts (token: String, categoryId : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getCategoryProductsUseCase(token, categoryId).collect { response ->
                withContext(Dispatchers.Main) {
                    when(response) {
                        is ApiResponse.Success -> state.value = CategoryProductsUIState(data = response.data)
                        is ApiResponse.Error -> state.value = CategoryProductsUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> state.value = CategoryProductsUIState(isLoading = true)
                    }
                }
            }
        }
    }
}