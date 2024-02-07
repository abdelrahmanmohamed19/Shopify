package com.example.gocart.di

import com.example.gocart.data.remote.ApiServices
import com.example.gocart.data.repository.CartRepositoryImpl
import com.example.gocart.data.repository.FavoritesRepositoryImpl
import com.example.gocart.data.repository.HomeRepositoryImpl
import com.example.gocart.data.repository.CategoryProductsRepositoryImpl
import com.example.gocart.data.repository.LoginRepositoryImpl
import com.example.gocart.data.repository.SignUpRepositoryImpl
import com.example.gocart.domain.repository.CartRepository
import com.example.gocart.domain.repository.FavoritesRepository
import com.example.gocart.domain.repository.HomeRepository
import com.example.gocart.domain.repository.CategoryProductsRepository
import com.example.gocart.domain.repository.LoginRepository
import com.example.gocart.domain.repository.SignUpRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    val client : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()
            val modifiedRequest = originalRequest.newBuilder()
                .header("lang", "en")
                .build()
            chain.proceed(modifiedRequest)
        }
        .callTimeout(60,TimeUnit.SECONDS)
        .readTimeout(60,TimeUnit.SECONDS)
        .writeTimeout(60,TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
     fun provideApiService () : ApiServices = Retrofit.Builder()
        .baseUrl("https://student.valuxapps.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiServices::class.java)

    @Provides
    @Singleton
    fun provideSignUpRepository(apiServices : ApiServices) : SignUpRepository = SignUpRepositoryImpl(apiServices)

    @Provides
    @Singleton
    fun provideSignInRepository(apiServices: ApiServices) : LoginRepository = LoginRepositoryImpl(apiServices)

    @Provides
    @Singleton
    fun provideHomeRepository(apiServices: ApiServices) : HomeRepository = HomeRepositoryImpl(apiServices)

    @Provides
    @Singleton
    fun provideFavoritesRepository(apiServices: ApiServices) : FavoritesRepository = FavoritesRepositoryImpl(apiServices)

    @Provides
    @Singleton
    fun provideCartRepository(apiServices: ApiServices) : CartRepository = CartRepositoryImpl(apiServices)

    @Provides
    @Singleton
    fun provideProductsRepository(apiServices: ApiServices) : CategoryProductsRepository = CategoryProductsRepositoryImpl(apiServices)
}