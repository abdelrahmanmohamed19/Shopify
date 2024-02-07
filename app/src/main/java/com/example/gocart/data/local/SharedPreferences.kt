package com.example.gocart.data.local

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy { context.getSharedPreferences("session", Context.MODE_PRIVATE) }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("user_token", token).apply()
    }

    fun fetchToken(): String? {
        return sharedPreferences.getString("user_token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("user_token").apply()
    }

    fun saveLoginStatus(status : Int) {
        sharedPreferences.edit().putInt("loginStatus",status).apply()
    }

    fun fetchLoginStatus () : Int {
        return sharedPreferences.getInt("loginStatus",0)
    }
}
