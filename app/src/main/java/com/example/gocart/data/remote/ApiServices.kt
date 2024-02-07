package com.example.gocart.data.remote

import com.example.gocart.data.remote.dto.AddRemoveProductDto
import com.example.gocart.data.remote.dto.FavoritesDto
import com.example.gocart.data.remote.dto.HomeDto
import com.example.gocart.data.remote.dto.LoginDto
import com.example.gocart.data.remote.dto.SignUpDto
import com.example.gocart.data.remote.dto.CartDto
import com.example.gocart.data.remote.dto.CategoryProductsDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServices {

    @POST("register")
    suspend fun signUp(
        @Query("email") email : String,
        @Query("password") password : String,
        @Query("name") name : String,
        @Query("phone") phone : String
    ) : SignUpDto

    @POST("login")
    suspend fun login(
        @Query("email") email : String,
        @Query("password") password : String
    ) : LoginDto

    @GET("home")
    suspend fun getHomeData(
        @Header("Authorization") token: String
    ) : HomeDto

    @GET("products")
    suspend fun getCategoryProducts(
        @Header("Authorization") token: String,
        @Query("category_id") id : Int
    ) : CategoryProductsDto

    @GET("favorites")
    suspend fun getFavoritesList(
        @Header("Authorization") token : String
    ) : FavoritesDto

    @POST("favorites")
    suspend fun addRemoveProductFromFavoritesList(
        @Header("Authorization") token : String,
        @Query("product_id") id : Int
    ) : AddRemoveProductDto

    @GET("carts")
    suspend fun getCartList(
        @Header("Authorization") token : String
    ) : CartDto

    @POST("carts")
    suspend fun addRemoveProductsFromCartList(
        @Header("Authorization") token : String,
        @Query("product_id") id : Int
    ) : AddRemoveProductDto
}
