package com.example.gocart.viewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocart.model.data.HomeData
import com.example.gocart.model.data.buy
import com.example.gocart.model.data.items
import com.example.gocart.model.repositories.CartRepository
import kotlinx.coroutines.launch

class CartViewModel (application: Application): AndroidViewModel(application){

    val Repo = CartRepository(application)

    private val _cartList = MutableLiveData<List<items>>()
    val cartList : LiveData<List<items>>
        get() = _cartList

    private val _cachedData = MutableLiveData<List<items>>()
    val cachedData: LiveData<List<items>>
        get() = _cachedData

    private val _cachedDatatotal = MutableLiveData<String>()
    val cachedDatatotal: LiveData<String>
        get() = _cachedDatatotal

    init {
        _cachedData.value = Repo.getCachedCartData()
        _cachedDatatotal.value = Repo.getCachedCartDataTotal()
    }

    fun getCart(token : String) {
        viewModelScope.launch {
            Repo.getCarts(token)
            val list = Repo.cartList.value
            _cartList.value = list !!
        }
    }
    fun Buy(context : Context, id : String) {
        Repo.Buy(context,id)
    }

}