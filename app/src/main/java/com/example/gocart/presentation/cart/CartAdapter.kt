package com.example.gocart.presentation.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gocart.R
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.databinding.CartitemBinding
import com.example.gocart.domain.model.CartItem

class CartAdapter (val context : Context,  private val cartViewModel: CartViewModel)  : RecyclerView.Adapter<CartAdapter.ViewHolder> (){

        private var cartList = emptyList<CartItem>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cartitem,parent,false)
            return ViewHolder(view,context)
        }

        override fun getItemCount() = cartList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = cartList[position]
            holder.binding.itemName.text = item.name
            holder.binding.itemPrice.text="Price: ${item.price} EG"
            holder.binding.quantity.text="Quantity: ${item.quantity}"
            holder.binding.deleteIcon.setOnClickListener {
                cartViewModel.addRemoveFromCart(AppPreferences(context).getToken()!!,item.id)
                Toast.makeText(context, "item is deleted", Toast.LENGTH_LONG).show()
            }
        }

        fun setData (list: List<CartItem>) {
            this.cartList = list
            notifyDataSetChanged()
        }

        //ViewHolder
        inner class ViewHolder(viewItem: View,val context: Context) : RecyclerView.ViewHolder(viewItem) {
            val binding = CartitemBinding.bind(viewItem)
        }
}