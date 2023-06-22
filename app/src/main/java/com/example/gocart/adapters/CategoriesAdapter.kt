package com.example.gocart.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gocart.R
import com.example.gocart.databinding.ProductesItemBinding
import com.example.gocart.model.data.Products
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.FavoritesViewModel

class CategoriesAdapter (val context : Context , private val favoritesViewModel: FavoritesViewModel, private val cartViewModel: CartViewModel)  : RecyclerView.Adapter<CategoriesAdapter.ViewHolder> (){

        private var CategoriesList = emptyList<Products>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.productes_item,parent,false)
            return ViewHolder(view,context)
        }

    override fun getItemCount() = CategoriesList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = CategoriesList[position]
            holder.binding.name.text=item.name.toString()
            holder.binding.newPrice.text=item.newPrice.toString()
            holder.binding.oldPrice.text=item.oldPrice.toString()
            holder.binding.id.text=item.id.toString()
            Glide.with(holder.itemView).load(item.image).into(holder.binding.image)
        }

        fun setData(list: List<Products>) {
            this.CategoriesList = list
            notifyDataSetChanged()
        }



        //ViewHolder
        inner class ViewHolder(viewItem: View,val context: Context) : RecyclerView.ViewHolder(viewItem), View.OnClickListener{
            val binding = ProductesItemBinding.bind(viewItem)
            init {
                binding.cart.setOnClickListener(this)
                binding.favorites.setOnClickListener(this)
            }

            override fun onClick(v: View?) {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val homeData = CategoriesList [position]
                    when (v?.id) {
                        R.id.cart -> {
                            cartViewModel.Buy(context,binding.id.text.toString())
                            Toast.makeText(context,"Added to Cart",Toast.LENGTH_SHORT).show()
                        }
                        R.id.favorites-> {
                            favoritesViewModel.AddorDelete(context,binding.id.text.toString())
                            Toast.makeText(context,"Added to Favorites",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

}