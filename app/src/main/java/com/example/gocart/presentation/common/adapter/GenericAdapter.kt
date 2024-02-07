package com.example.gocart.presentation.common.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.databinding.ProductesItemBinding
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.presentation.cart.CartViewModel
import com.example.gocart.presentation.favorites.FavoritesViewModel

class GenericAdapter(
    private val context: Context,
    private val favoritesViewModel: FavoritesViewModel,
    private val cartViewModel: CartViewModel
): RecyclerView.Adapter<GenericAdapter.ViewHolder> () {

    private var productsList = emptyList<ProductItems> ()
    val token = AppPreferences(context).getToken() ?: ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.productes_item,parent,false)
        return ViewHolder(view,context)
    }

    override fun getItemCount() = productsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productsList[position]
        Glide.with(holder.itemView).load(currentItem.image).into(holder.binding.image)
        holder.binding.description.text= currentItem.name
        holder.binding.newPrice.text= "${currentItem.newPrice} EG"

        holder.binding.favoritesIcon.apply {
            setImageResource(if (currentItem.isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
            setOnClickListener {
                favoritesViewModel.addRemoveFromFavorites(token, currentItem.id.toInt())
                currentItem.isFavorite = !currentItem.isFavorite
                setImageResource(if (currentItem.isFavorite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24)
            }
        }

        holder.binding.cardView.setOnClickListener {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.product_description_dialogue, null)
            val dialog = AlertDialog.Builder(context).setView(dialogView).create()
            val descriptionTextView = dialogView.findViewById<TextView>(R.id.productDescriptionTextView)
            val quantityPicker = dialogView.findViewById<NumberPicker>(R.id.quantityPicker)
            val addToCartButton = dialogView.findViewById<Button>(R.id.addToCartButton)
            descriptionTextView.text = currentItem.description
            quantityPicker.minValue = 1
            quantityPicker.maxValue = 10
            addToCartButton.setOnClickListener {
                cartViewModel.addRemoveFromCart(token, currentItem.id.toInt())
                dialog.dismiss()
            }
            dialog.show()
        }
    }

    fun setData(list: List<ProductItems>) {
        this.productsList = list
        notifyDataSetChanged()
    }

    // View Holder
    inner class ViewHolder(viewItem: View, val context: Context): RecyclerView.ViewHolder(viewItem) {
        val binding = ProductesItemBinding.bind(viewItem)
    }
}