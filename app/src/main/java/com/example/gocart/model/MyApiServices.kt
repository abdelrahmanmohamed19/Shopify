package com.example.gocart.model

import com.example.gocart.model.data.AddORDelete
import com.example.gocart.model.data.Categories
import com.example.gocart.model.data.DefaultResponse
import com.example.gocart.model.data.Favorites
import com.example.gocart.model.data.Home
import com.example.gocart.model.data.LoginResponse
import com.example.gocart.model.data.buy
import com.example.gocart.model.data.cart
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface MyApiServices {

    @POST("register")
    fun Signup(
        @Query("email") email : String,
        @Query("password") password : String,
        @Query("name") name : String,
        @Query("phone") phone : String
    ) : Call<DefaultResponse>

    @POST("login")
    fun Loginin(
        @Query("email") email : String,
        @Query("password") password : String
    ) : Call<LoginResponse>

    @GET("home")
    suspend fun getHome() : Response<Home>

    @GET("categories")
    suspend fun getCategories() : Response<Categories>

    @GET("products")
    suspend fun getProducts (@Query("category_id") id : Int) : Response<Categories>

    @GET("favorites")
    suspend fun getFavorites (@Header("Authorization") token : String) : Response<Favorites>

    @POST("favorites")
    fun AddORDelete(@Header("Authorization") token : String, @Query("product_id") id : Int) : Call<AddORDelete>

    @GET("carts")
    suspend fun getCart(@Header("Authorization") token : String) : Response<cart>

    @POST("carts")
    fun Buy(@Header("Authorization") token : String, @Query("product_id") id : Int) : Call<buy>


}