package com.example.gocart.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.adapters.HomeAdapter
import com.example.gocart.databinding.FragmentFavoritesBinding
import com.example.gocart.model.SessionManager
import com.example.gocart.viewModel.CartViewModel
import com.example.gocart.viewModel.FavoritesViewModel


class FavoritesFragment : Fragment() {

    lateinit var binding : FragmentFavoritesBinding
    lateinit var viewModel :  FavoritesViewModel
    lateinit var cartViewModel : CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        cartViewModel= ViewModelProvider(this).get(CartViewModel::class.java)
        binding.lifecycleOwner = this
        val token = SessionManager(requireContext()).fetchToken()
        viewModel.getFavorites(token.toString())
        val adapter = HomeAdapter(requireContext(),cartViewModel,viewModel)
        binding.FavoritesRecycler.adapter = adapter
        viewModel.cachedData.observe(viewLifecycleOwner){ adapter.setData(it) }
        }
    }