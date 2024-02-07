package com.example.gocart.data.remote.dto


data class FavoritesDto (
    val status : Boolean,
    val message : String,
    val data : FavoritesList
)

data class FavoritesList (
    val data : List<ProductData>
)


