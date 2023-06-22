package com.example.gocart.model.repositories

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.gocart.model.MyApi
import com.example.gocart.model.MyApiServices
import com.example.gocart.model.SessionManager
import com.example.gocart.model.data.AddORDelete
import com.example.gocart.model.data.HomeData
import com.example.gocart.model.data.buy
import com.example.gocart.model.data.items
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartRepository (context: Context){

        private val api: MyApiServices = MyApi().retrofit



    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveCartData(buy: List<items>) {
        val gson = Gson()
        val json = gson.toJson(buy)
        sharedPreferences.edit().putString("cartData", json).apply()
    }

    fun getCachedCartData(): List<items> {
        val json = sharedPreferences.getString("cartData", null)
        val gson = Gson()
        val type = object : TypeToken<List<items>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    fun saveCartDataTotal(total : String) {
        val gson = Gson()
        val json = gson.toJson(total)
        sharedPreferences.edit().putString("totalData", json).apply()
    }

    fun getCachedCartDataTotal(): String {
        val json = sharedPreferences.getString("totalData", null)
        val gson = Gson()
        val type = object : TypeToken<String>() {}.type
        return gson.fromJson(json, type) ?: ""
    }


    val cartList = MutableLiveData<List<items>>()
        val totalPrice = MutableLiveData<String>()

        suspend fun getCarts (token : String) {
            val response = api.getCart(token)
            if(response.isSuccessful) {
                val responseBody = response.body()
                val CartProducts = responseBody?.data?.cartItems
                if (!CartProducts.isNullOrEmpty()) {
                    for (cartProduct in CartProducts) {
                        val list = CartProducts.map {
                            val id = it?.id.toString()
                            val product = it?.product
                            val quantity = "Quantity: ${it?.quantity.toString()}"
                            val productId = it?.id
                            val price = "Price: ${product?.price.toString()} EG"
                            val iid = product?.id.toString()
                            val oldPrice =  "Old Price: ${product?.oldPrice.toString()} EG"
                            val discount = product?.discount.toString()
                            val image = product?.image.toString()
                            val name = "Item: ${product?.name.toString()}"
                            val description = product?.description.toString()
                            items(name,price,quantity, iid)
                        }
                        val total = responseBody?.data?.total.toString()
                        totalPrice.value = total
                        cartList.value = list
                        saveCartData(list)
                        saveCartDataTotal(total)
                    }
                } else {
                    Log.d("Favorite Products", "No favorite products found")
                }
            }
        }

        fun Buy(context : Context, id : String) {
            val token = SessionManager(context).fetchToken()
            val call = api.Buy(token.toString(),id.toInt())
            call.enqueue(object  : Callback<buy> {
                override fun onResponse(call: Call<buy>, response: Response<buy>) {
                    val response = response.body()
                    val message = response?.message.toString()
                }
                override fun onFailure(call: Call<buy>, t: Throwable) {
                }
            })
        }
    }