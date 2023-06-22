package com.example.gocart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gocart.adapters.HomeAdapter
import com.example.gocart.databinding.FragmentHomeBinding
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.FavoritesViewModel
import com.example.gocart.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel : HomeViewModel
    lateinit var cartViewModel : CartViewModel
    lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        cartViewModel= ViewModelProvider(this).get(CartViewModel::class.java)
        favoritesViewModel= ViewModelProvider(this).get(FavoritesViewModel::class.java)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeRecycler.layoutManager = LinearLayoutManager(requireContext())
        val adapter = HomeAdapter(requireContext(),cartViewModel,favoritesViewModel)
        binding.homeRecycler.adapter = adapter
        viewModel.getHome()
        viewModel.cachedData.observe(viewLifecycleOwner) {
          adapter.setData(it)
        }

    }
    }
