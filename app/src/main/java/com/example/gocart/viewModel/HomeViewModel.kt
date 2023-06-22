package com.example.gocart.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.model.data.HomeData
import com.example.gocart.model.repositories.HomeRepository
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get

class HomeViewModel (application: Application): AndroidViewModel(application){

    private val Repo = HomeRepository(application)

    private val _cachedData = MutableLiveData<List<HomeData>>()
    val cachedData: LiveData<List<HomeData>>
        get() = _cachedData

    init {
        _cachedData.value = Repo.getCachedHomeData()
    }

    private val _data = MutableLiveData<List<HomeData>>()
    val data : LiveData<List<HomeData>>
        get() = _data

    fun getHome() {
        viewModelScope.launch {
            val Data = Repo.getHome()
            val RepoData = Repo.homeList
            _data.value = RepoData.value
        }

    }

}