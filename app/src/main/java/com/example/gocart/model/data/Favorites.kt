package com.example.gocart.model.data


import com.google.gson.annotations.SerializedName

data class Favorites(
@SerializedName("data")
val `data`: FData? ,
@SerializedName("message")
val message: Any? = Any(),
@SerializedName("status")
val status: Boolean? = false
)

data class FData(
    @SerializedName("current_page")
    val currentPage: Int? = 0,
    @SerializedName("data")
    val `data`: List<FFData>,
    @SerializedName("first_page_url")
    val firstPageUrl: String? = "",
    @SerializedName("from")
    val from: Int? = 0,
    @SerializedName("last_page")
    val lastPage: Int? = 0,
    @SerializedName("last_page_url")
    val lastPageUrl: String? = "",
    @SerializedName("next_page_url")
    val nextPageUrl: Any? = Any(),
    @SerializedName("path")
    val path: String? = "",
    @SerializedName("per_page")
    val perPage: Int? = 0,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any? = Any(),
    @SerializedName("to")
    val to: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)
data class FFData(
    @SerializedName("product")
    val product: FProduct? = null,
    @SerializedName("id")
    val id: Int? = null
)

data class FProduct(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("discount")
    val discount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("old_price")
    val oldPrice: Int? = null,
    @SerializedName("price")
    val price: Int? = null
)
