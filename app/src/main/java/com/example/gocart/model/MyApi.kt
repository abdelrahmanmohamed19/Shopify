package com.example.gocart.model

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApi {
    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("lang", "en")
                .build()
            chain.proceed(modifiedRequest)
        }
        .build()

     val retrofit= Retrofit.Builder()
        .baseUrl("https://student.valuxapps.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
         .client(client)
        .build()
         .create(MyApiServices::class.java)
}

