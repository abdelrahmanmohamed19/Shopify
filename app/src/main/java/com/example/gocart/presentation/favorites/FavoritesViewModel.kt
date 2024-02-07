package com.example.gocart.presentation.favorites

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.common.ApiResponse
import com.example.gocart.common.Utils
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.domain.use_case.AddOrRemoveToFavoritesUseCase
import com.example.gocart.domain.use_case.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor (private val getFavoritesUseCase: GetFavoritesUseCase, private val addOrRemoveToFavoritesUseCase: AddOrRemoveToFavoritesUseCase ) : ViewModel() {

    private var _favoritesList = MutableStateFlow(emptyList<ProductItems>())
    val favoritesList get() = _favoritesList

    var isLoading = MutableStateFlow(true)
    var isSuccess = MutableStateFlow(false)

    fun getFavoritesList (token: String, context: Context, view : View)  {
        getFavoritesUseCase(token).onEach {
            when (it) {
                is ApiResponse.Success -> {
                    getFavoritesUseCase.favoritesList.collect { productItemsList ->
                        _favoritesList.value = productItemsList
                        isLoading.value = false
                        isSuccess.value = true
                    }
                }
                is ApiResponse.Error -> {
                    isLoading.value = false
                    isSuccess.value = false
                    Utils.showSnackbar(view, it.message, { getFavoritesList(token,context,view) }, context)
                }
                is ApiResponse.Loading -> isLoading.value = true
            }
        }.launchIn(viewModelScope)
    }

    fun addOrRemoveToFavorites (token : String, id : Int, context: Context) {

        addOrRemoveToFavoritesUseCase(token, id).onEach {
            when (it) {
                is ApiResponse.Success -> {
                    val updatedList = _favoritesList.value.toMutableList()
                    updatedList.removeIf { it.id == id.toString() }
                    _favoritesList.value = updatedList
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is ApiResponse.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }
}
