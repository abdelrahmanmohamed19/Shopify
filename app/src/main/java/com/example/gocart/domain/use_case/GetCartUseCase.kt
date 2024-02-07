package com.example.gocart.domain.use_case

import com.example.gocart.common.ApiResponse
import com.example.gocart.domain.model.CartItem
import com.example.gocart.domain.repositories.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCartUseCase @Inject constructor(private val cartRepository: CartRepository) {

    private var _cartList = MutableStateFlow(emptyList<CartItem>())
    val cartList get() = _cartList

    private var _totalPrice = ""
    val totalPrice get() = _totalPrice

   operator fun invoke (token : String) : Flow<ApiResponse> = flow {
        try {
            emit(ApiResponse.Loading(""))
            val data = cartRepository.getCartList(token)
                _totalPrice = data.data.total.toString()
                data.data.cartItems.forEach {
                    _cartList.value = _cartList.value + CartItem(
                        id = it.product.id,
                        name = it.product.name,
                        quantity = it.quantity.toString(),
                        price = it.product.price.toString()
                    )
                }
            emit(ApiResponse.Success(""))
        } catch (e : HttpException) {
            emit(ApiResponse.Error("an error has occurred please try again"))
        } catch (e : IOException) {
            emit(ApiResponse.Error("no internet connection"))
        }
    }
}