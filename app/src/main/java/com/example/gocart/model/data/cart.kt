package com.example.gocart.model.data


import com.google.gson.annotations.SerializedName

data class cart(
    @SerializedName("data")
    val `data`: DataX? = DataX(),
    @SerializedName("message")
    val message: Any? = Any(),
    @SerializedName("status")
    val status: Boolean? = false
)

data class DataX(
    @SerializedName("cart_items")
    val cartItems: List<CartItem?>? = null,
    @SerializedName("sub_total")
    val subTotal: Double? = null,
    @SerializedName("total")
    val total: Double? = null
)

data class CartItem(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("product")
    val product: Productt? = null,
    @SerializedName("quantity")
    val quantity: Int? = null
)
data class Productt(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("images")
    val images: List<String?>? = null,
    @SerializedName("in_cart")
    val inCart: Boolean? = null,
    @SerializedName("in_favorites")
    val inFavorites: Boolean? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("old_price")
    val oldPrice: Double? = null,
    @SerializedName("price")
    val price: Double? = null
)