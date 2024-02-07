package com.example.gocart.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.data.ApiResponse
import com.example.gocart.domain.use_case.AddRemoveFromFavoritesUseCase
import com.example.gocart.domain.use_case.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor (
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val addRemoveFromFavoritesUseCase: AddRemoveFromFavoritesUseCase
): ViewModel() {

   var favoritesUIState = MutableStateFlow(FavoritesUIState())
       private set

    var addRemoveUIState = MutableStateFlow(AddRemoveFavoritesUIState())
        private set

    fun getFavoritesList (token: String){
        viewModelScope.launch(Dispatchers.IO) {
           getFavoritesUseCase(token).collect{ response ->
               withContext(Dispatchers.Main) {
                   when(response) {
                       is ApiResponse.Success -> favoritesUIState.value = FavoritesUIState(data = response.data)
                       is ApiResponse.Error -> favoritesUIState.value = FavoritesUIState(errorMessage = response.message)
                       is ApiResponse.Loading -> favoritesUIState.value = FavoritesUIState(isLoading = true)
                   }
               }
           }
        }
    }

    fun addRemoveFromFavorites (token : String, id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            addRemoveFromFavoritesUseCase(token, id).collect{ response ->
                withContext(Dispatchers.Main) {
                    when(response) {
                        is ApiResponse.Success -> {
                            addRemoveUIState.value = AddRemoveFavoritesUIState(data = response.data)
                            getFavoritesList(token)
                        }
                        is ApiResponse.Error -> addRemoveUIState.value = AddRemoveFavoritesUIState(errorMessage = response.message)
                        is ApiResponse.Loading -> addRemoveUIState.value = AddRemoveFavoritesUIState(isLoading = true)
                    }
                }
            }
        }
    }
}
