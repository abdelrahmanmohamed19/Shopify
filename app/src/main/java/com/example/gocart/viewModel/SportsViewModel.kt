package com.example.gocart.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.gocart.model.data.Products
import com.example.gocart.model.repositories.LightingRepository
import com.example.gocart.model.repositories.SportsRepository
import kotlinx.coroutines.launch

class SportsViewModel(application: Application): AndroidViewModel(application) {

    val Repo = SportsRepository(application)

    private val _cachedData = MutableLiveData<List<Products>>()
    val cachedData: LiveData<List<Products>>
        get() = _cachedData

    init {
        _cachedData.value = Repo.getCachedproductesData()
    }

    private val _ProductesData = MutableLiveData<List<Products>>()

    val ProductesData : LiveData<List<Products>>
        get() = _ProductesData

    fun getProductes(id : Int){
        viewModelScope.launch {
            val data = Repo.getProducts(id)
            val RepoData = Repo.MyProducts
            _ProductesData.value = RepoData.value
        }
    }
}
