package com.example.gocart.model.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.gocart.model.MyApi
import com.example.gocart.model.MyApiServices
import com.example.gocart.model.SessionManager
import com.example.gocart.model.data.AddORDelete
import com.example.gocart.model.data.HomeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FavoritesRepository (context: Context){

    private val api: MyApiServices = MyApi().retrofit


    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveFavoritesData(favoData: List<HomeData>) {
        val gson = Gson()
        val json = gson.toJson(favoData)
        sharedPreferences.edit().putString("favoData", json).apply()
    }

    fun getCachedFavoritesData(): List<HomeData> {
        val json = sharedPreferences.getString("favoData", null)
        val gson = Gson()
        val type = object : TypeToken<List<HomeData>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }


    val favoritesList = MutableLiveData<List<HomeData>>()

    suspend fun getFavorites (token : String) {
        val response = api.getFavorites(token)
        if(response.isSuccessful) {
            val responseBody = response.body()
            val favoriteProducts = responseBody?.data?.data
                if (!favoriteProducts.isNullOrEmpty()) {
                    for (favoriteProduct in favoriteProducts) {
                        val list = favoriteProducts.map {
                            val id = it.id.toString()
                            val product = it.product
                            val productId = it.id
                            val price = "New Price: ${product?.price.toString()} EG"
                            val oldPrice =  "Old Price: ${product?.oldPrice.toString()} EG"
                            val discount = product?.discount.toString()
                            val image = product?.image.toString()
                            val name = product?.name.toString()
                            val description = product?.description.toString()
                            HomeData(price,oldPrice,image,name,id)
                        }
                        favoritesList.value = list
                        saveFavoritesData(list)
                    }
                } else {
                    Log.d("Favorite Products", "No favorite products found")
                }
        }
    }

    fun AddORDelete(context : Context, id : String) {
        val token = SessionManager(context).fetchToken()
       val call = api.AddORDelete(token.toString(),id.toInt())
        call.enqueue(object  : Callback<AddORDelete> {
            override fun onResponse(call: Call<AddORDelete>, response: Response<AddORDelete>) {
                val response = response.body()
                val message = response?.message.toString()
            }
            override fun onFailure(call: Call<AddORDelete>, t: Throwable) {
            }
        })
    }
}