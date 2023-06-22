package com.example.gocart.adapters

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.databinding.CartitemBinding
import com.example.gocart.databinding.ProductesItemBinding
import com.example.gocart.model.data.Products
import com.example.gocart.model.data.buy
import com.example.gocart.model.data.items
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.FavoritesViewModel

class CartAdapter (val context : Context,  private val cartViewModel: CartViewModel)  : RecyclerView.Adapter<CartAdapter.ViewHolder> (){

        private var CartList = emptyList<items>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.cartitem,parent,false)
            return ViewHolder(view,context)
        }

        override fun getItemCount() = CartList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = CartList[position]
            holder.binding.name.text = item.name.toString()
            holder.binding.id.text = item.id.toString()
            holder.binding.price.text=item.price.toString()
            holder.binding.quantity.text=item.quantity.toString()
        }

        fun setData(list: List<items>) {
            this.CartList = list
            notifyDataSetChanged()
        }



        //ViewHolder
        inner class ViewHolder(viewItem: View,val context: Context) : RecyclerView.ViewHolder(viewItem), View.OnClickListener{
            val binding = CartitemBinding.bind(viewItem)
            init {
                binding.cart.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val homeData = CartList [position]
                    when (v?.id) {
                        R.id.cart -> {
                            cartViewModel.Buy(context,binding.id.text.toString())
                            Toast.makeText(context,"Added to Cart",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

}