package com.example.gocart.data.remote

import com.example.gocart.data.remote.dto.AddAndRemoveProductDto
import com.example.gocart.data.remote.dto.FavoritesDto
import com.example.gocart.data.remote.dto.HomeDto
import com.example.gocart.data.remote.dto.LoginDto
import com.example.gocart.data.remote.dto.SignUpDto
import com.example.gocart.data.remote.dto.CartDto
import com.example.gocart.data.remote.dto.CategoriesProductsDto
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
    suspend fun signIn(
        @Query("email") email : String,
        @Query("password") password : String
    ) : LoginDto

    @GET("home")
    suspend fun getHomeProducts() : HomeDto

    @GET("products")
    suspend fun getCategoriesProducts (
        @Query("category_id") id : Int
    ) : CategoriesProductsDto

    @GET("favorites")
    suspend fun getFavoritesList (
        @Header("Authorization") token : String
    ) : FavoritesDto

    @POST("favorites")
    suspend fun addOrRemoveProductToFavouritesList(
        @Header("Authorization") token : String,
        @Query("product_id") id : Int
    ) : AddAndRemoveProductDto

    @GET("carts")
    suspend fun getCartList(
        @Header("Authorization") token : String
    ) : CartDto

    @POST("carts")
    suspend fun addOrRemoveProductToCartList(
        @Header("Authorization") token : String,
        @Query("product_id") id : Int
    ) : AddAndRemoveProductDto
}
