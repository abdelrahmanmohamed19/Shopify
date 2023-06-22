package com.example.gocart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gocart.R
import com.example.gocart.adapters.CategoriesAdapter
import com.example.gocart.databinding.FragmentClothesBinding
import com.example.gocart.databinding.FragmentSportsBinding
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.ClothesViewModel
import com.example.gocart.viewModel.FavoritesViewModel
import com.example.gocart.viewModel.ProductesViewModel

class ClothesFragment : Fragment() {

    lateinit var binding : FragmentClothesBinding
    lateinit var viewModel : ClothesViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var cartViewModel: CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentClothesBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ClothesViewModel::class.java)
        favoritesViewModel= ViewModelProvider(this).get(FavoritesViewModel::class.java)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoriesAdapter(requireContext(),favoritesViewModel,cartViewModel)
        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getProductes(46)
        viewModel.cachedData.observe(viewLifecycleOwner){
            adapter.setData(it)
        }
    }
}