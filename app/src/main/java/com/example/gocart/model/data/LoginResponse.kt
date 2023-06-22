package com.example.gocart.model.data

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: user?
)

data class user(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image") val image: String,
    @SerializedName("points") val points : Double,
    @SerializedName("credit")val Credit : Double,
    @SerializedName("token") val token: String
)
