package com.example.gocart.model.repositories

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.gocart.model.MyApi
import com.example.gocart.model.MyApiServices
import com.example.gocart.model.data.HomeData
import com.example.gocart.model.data.Products
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductesRepository (context : Context){

    private val api: MyApiServices = MyApi().retrofit

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveProductesData(productesData: List<Products>) {
        val gson = Gson()
        val json = gson.toJson(productesData)
        sharedPreferences.edit().putString("productesData", json).apply()
    }

    fun getCachedproductesData(): List<Products> {
        val json = sharedPreferences.getString("productesData", null)
        val gson = Gson()
        val type = object : TypeToken<List<Products>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun clearCachedProductesData() {
        sharedPreferences.edit().remove("productesData").apply()
    }



    val MyProducts = MutableLiveData<List<Products>>()

    suspend fun getProducts(id : Int){
        val response = api.getProducts(id)
        if(response.isSuccessful) {
            val responseBody = response.body()
            val data = response.body()?.data?.Data
            val list = data?.map {
                val name = it.name.toString()
                val image = it.image.toString()
                val newPrice = "New Price: ${it.NewPrice} EG"
                val oldPrice = "Old Price: ${it.OldPrice} EG"
                val discount = it.discount.toString()
                val favorites = it.favorites
                val cart = it.cart
                val id =it.id.toString()
                Products(newPrice,oldPrice,image,name, discount,favorites,cart,id)
            }
            MyProducts.value = list !!

            saveProductesData(list)

            Handler(Looper.getMainLooper()).postDelayed({
                Log.i("AbdoRepo", "${getCachedproductesData().toString()}")
            }, 1000)
        }
    }
}