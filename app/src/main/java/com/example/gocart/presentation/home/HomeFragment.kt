package com.example.gocart.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gocart.common.GenericAdapter
import com.example.gocart.databinding.FragmentHomeBinding
import com.example.gocart.presentation.cart.CartViewModel
import com.example.gocart.presentation.favorites.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding !!
    private val homeViewModel by viewModels<HomeViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private val genericAdapter by lazy { GenericAdapter(requireContext(), favoritesViewModel, cartViewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Home"
        binding.recyclerView.adapter = genericAdapter
        homeViewModel.getHomeProducts(requireContext(),view)
        lifecycleScope.launch {
            homeViewModel.apply {
                isLoading.collect{
                    when (it) {
                        true -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.INVISIBLE
                            binding.emptyListText.visibility = View.INVISIBLE
                        }
                        false -> {
                            isSuccess.collect {
                                when(it) {
                                    true -> {
                                        homeProductsData.collect {
                                            if (it.isNotEmpty()) {
                                                binding.progressBar.visibility = View.INVISIBLE
                                                binding.recyclerView.visibility = View.VISIBLE
                                                binding.emptyListText.visibility = View.INVISIBLE
                                                genericAdapter.setData(it)
                                            } else {
                                                binding.progressBar.visibility = View.INVISIBLE
                                                binding.recyclerView.visibility = View.INVISIBLE
                                                binding.emptyListText.visibility = View.VISIBLE
                                            }
                                        }
                                    }
                                    false -> {
                                        binding.progressBar.visibility = View.INVISIBLE
                                        binding.recyclerView.visibility = View.INVISIBLE
                                        binding.emptyListText.visibility = View.INVISIBLE
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
