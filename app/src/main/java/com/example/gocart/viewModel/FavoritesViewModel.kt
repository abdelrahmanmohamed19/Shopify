package com.example.gocart.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gocart.model.data.HomeData
import com.example.gocart.model.repositories.FavoritesRepository
import kotlinx.coroutines.launch

class FavoritesViewModel (application: Application): AndroidViewModel(application){

   private val Repo = FavoritesRepository(application)


    private val _cachedData = MutableLiveData<List<HomeData>>()
    val cachedData: LiveData<List<HomeData>>
        get() = _cachedData

    init {
        _cachedData.value = Repo.getCachedFavoritesData()
    }



    private val _favoritesList = MutableLiveData<List<HomeData>>()

    val favoritesList : LiveData<List<HomeData>>
        get() = _favoritesList


    fun getFavorites(Token : String) {
        viewModelScope.launch {
            Repo.getFavorites(Token)
            val List = Repo.favoritesList
            _favoritesList.value = List.value !!
        }
    }

    fun AddorDelete(context : Context, id : String) {
        Repo.AddORDelete(context,id)
    }
}