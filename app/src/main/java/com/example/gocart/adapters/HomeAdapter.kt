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
import com.example.gocart.model.data.HomeData
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.FavoritesViewModel

class HomeAdapter(val context : Context,private val cartViewModel: CartViewModel , private val favoritesViewModel: FavoritesViewModel) : RecyclerView.Adapter<HomeAdapter.viewHolder> (){

    private var HomeList = emptyList<HomeData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
      val view =LayoutInflater.from(parent.context).inflate(R.layout.productes_item,parent,false)
        return viewHolder(view,context)
    }

    override fun getItemCount() = HomeList.size

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
       val item = HomeList[position]
        holder.binding.newPrice.text=item.newPrice.toString()
        holder.binding.oldPrice.text=item.oldPrice.toString()
        holder.binding.name.text=item.name.toString()
        holder.binding.id.text = item.id.toString()
        Glide.with(holder.itemView).load(item.image).into(holder.binding.image)
    }

        fun setData(list: List<HomeData>) {
                this.HomeList = list
                notifyDataSetChanged()
            }



    //ViewHolder
   inner class viewHolder(viewItem: View,val context: Context) : RecyclerView.ViewHolder(viewItem), View.OnClickListener {
        val binding = ProductesItemBinding.bind(viewItem)

        init {
            binding.cart.setOnClickListener(this)
            binding.favorites.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val homeData = HomeList[position]
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