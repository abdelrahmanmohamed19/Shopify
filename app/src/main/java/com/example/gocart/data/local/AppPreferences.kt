package com.example.gocart.data.local

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy { context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE) }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("user_token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("user_token", null)
    }

    fun deleteToken() {
        sharedPreferences.edit().remove("user_token").apply()
    }

    fun saveLoginStatus(status : Boolean) {
        sharedPreferences.edit().putBoolean("loginStatus",status).apply()
    }

    fun getLoginStatus () : Boolean {
        return sharedPreferences.getBoolean("loginStatus",false)
    }
}
