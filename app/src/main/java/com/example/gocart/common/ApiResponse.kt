package com.example.gocart.common

sealed class ApiResponse {
    
    data class Success (val message : String) : ApiResponse()
    data class Error (val message : String) : ApiResponse()
    data class Loading (val message : String) : ApiResponse()
    
}