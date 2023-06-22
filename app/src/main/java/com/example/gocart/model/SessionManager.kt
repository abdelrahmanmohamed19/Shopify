package com.example.gocart.model

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.preference.Preference
import androidx.preference.PreferenceManager

class SessionManager(val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("session", Context.MODE_PRIVATE)
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("user_token", token).apply()
    }

    fun fetchToken(): String? {
        return sharedPreferences.getString("user_token", null)
    }

    fun clearToken() {
        sharedPreferences.edit().remove("user_token").apply()
    }
}
