package com.example.gocart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gocart.adapters.CategoriesAdapter
import com.example.gocart.databinding.FragmentElectrionicsBinding
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.FavoritesViewModel
import com.example.gocart.viewModel.ProductesViewModel

class ElectrionicsFragment : Fragment() {

    lateinit var binding : FragmentElectrionicsBinding
    lateinit var viewModel : ProductesViewModel
    lateinit var favoritesViewModel: FavoritesViewModel
    lateinit var   cartViewModel : CartViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentElectrionicsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(ProductesViewModel::class.java)
        favoritesViewModel= ViewModelProvider(this).get(FavoritesViewModel::class.java)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CategoriesAdapter(requireContext(),favoritesViewModel,  cartViewModel)
        binding.Recycler.adapter = adapter
        binding.Recycler.layoutManager = LinearLayoutManager(requireContext())
        viewModel.cachedData.observe(viewLifecycleOwner){ adapter.setData(it) }
        viewModel.getProductes(44)
    }

}