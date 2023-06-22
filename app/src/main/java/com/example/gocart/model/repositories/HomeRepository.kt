package com.example.gocart.model.repositories

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.gocart.model.MyApi
import com.example.gocart.model.MyApiServices
import com.example.gocart.model.data.Home
import com.example.gocart.model.data.HomeData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HomeRepository(context: Context) {

    private val api: MyApiServices = MyApi().retrofit

    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveHomeData(homeData: List<HomeData>) {
        val gson = Gson()
        val json = gson.toJson(homeData)
        sharedPreferences.edit().putString("homeData", json).apply()
    }

    fun getCachedHomeData(): List<HomeData> {
        val json = sharedPreferences.getString("homeData", null)
        val gson = Gson()
        val type = object : TypeToken<List<HomeData>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }


    val homeList = MutableLiveData<List<HomeData>>()
    suspend fun getHome() {
        val response = api.getHome()
        if (response.isSuccessful) {
            val homeData = response.body()
            val product = homeData?.data?.products
            val list = product?.map{
                val newPrice = "New Price : " + it?.price.toString()+" EG"
                val oldPrice = "Old Price : " + it?.oldPrice.toString()+" EG"
                val image = it?.image.toString()
                val name = it?.name.toString()
                val id =it?.id.toString()
                HomeData(newPrice, oldPrice, image, name,id)
            }
            homeList.value = list!!

            saveHomeData(list)

            Handler(Looper.getMainLooper()).postDelayed({
                Log.i("AbdoRepo", "${getCachedHomeData().toString()}")
            }, 1000)
        }
        else {
            homeList.postValue(emptyList())
        }
    }

}






