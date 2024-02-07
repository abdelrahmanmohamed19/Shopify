package com.example.gocart.data.remote.dto

data class User(
    val credit: Int,
    val email: String,
    val id: Int,
    val image: String,
    val name: String,
    val phone: String,
    val points: Int,
    val token: String
)