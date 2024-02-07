package com.example.gocart.presentation.electronics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gocart.common.GenericAdapter
import com.example.gocart.databinding.FragmentElectrionicsBinding
import com.example.gocart.presentation.cart.CartViewModel
import com.example.gocart.presentation.favorites.FavoritesViewModel
import com.example.gocart.presentation.ui.ProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
@AndroidEntryPoint
class ElectronicsFragment : Fragment() {

    private var _binding : FragmentElectrionicsBinding? = null
    private val binding get() = _binding !!
    private val viewModel by viewModels<ProductsViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private val genericAdapter by lazy { GenericAdapter(requireContext(), favoritesViewModel, cartViewModel) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentElectrionicsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Electronics"
        binding.recyclerView.adapter = genericAdapter
        viewModel.getCategoriesProducts(44, requireContext(),view)
        lifecycleScope.launch {
            viewModel.apply {
                isLoading.collect{
                    when (it) {
                        true -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.INVISIBLE
                            binding.emptyListText.visibility = View.INVISIBLE
                        }
                        false -> {
                            isSuccess.collect{
                                when(it) {
                                    true -> {
                                        productsList.collect{
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