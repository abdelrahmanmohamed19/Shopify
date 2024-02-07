package com.example.gocart.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.data.local.SharedPreferences
import com.example.gocart.databinding.ProductesItemBinding
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.presentation.cart.CartViewModel
import com.example.gocart.presentation.favorites.FavoritesViewModel

class GenericAdapter (val context : Context, private val favoritesViewModel: FavoritesViewModel, private val cartViewModel: CartViewModel)  : RecyclerView.Adapter<GenericAdapter.ViewHolder> () {

    private var productItems = emptyList<ProductItems> ()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.productes_item,parent,false)
        return ViewHolder(view,context)
    }

    override fun getItemCount() = productItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productItems[position]
        Glide.with(holder.itemView).load(currentItem.image).into(holder.binding.image)
        holder.binding.description.text=currentItem.name
        holder.binding.newPrice.text = "New Price : ${currentItem.newPrice}"
        holder.binding.oldPrice.text= "Old Price : ${ currentItem.oldPrice }"
        holder.binding.cartIcon.setOnClickListener{cartViewModel.addOrRemoveToCart(SharedPreferences(context).fetchToken() !!,currentItem.id.toInt(),context)}
        holder.binding.favoritesIcon.setOnClickListener{
            favoritesViewModel.addOrRemoveToFavorites(SharedPreferences(context).fetchToken() !!,currentItem.id.toInt(),context)
        }
    }

    fun setData(list: List<ProductItems>) {
        this.productItems = list
        notifyDataSetChanged()
    }

    //ViewHolder
    inner class ViewHolder(viewItem: View, val context: Context) : RecyclerView.ViewHolder(viewItem) {
        val binding = ProductesItemBinding.bind(viewItem)
    }
}