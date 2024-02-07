package com.example.gocart.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.presentation.common.adapter.GenericAdapter
import com.example.gocart.databinding.FragmentHomeBinding
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.presentation.cart.CartViewModel
import com.example.gocart.presentation.favorites.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
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
        binding.recyclerView.adapter = genericAdapter
        val token = AppPreferences(requireContext()).getToken() ?: ""

        homeViewModel.getHomeProducts(token)

        binding.apply {
            recyclerView.visibility = View.GONE
            errorMessageText.visibility = View.GONE
            tryAgainButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            welcomeText.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.state.collect{ state ->
                val homeDataList =  state.data?.data?.products?.map { data ->
                    ProductItems(
                        id = data.id.toString(),
                        image = data.image,
                        name = data.name,
                        oldPrice = data.old_price.toString(),
                        newPrice = data.price.toString(),
                        description = data.description,
                        isFavorite = data.in_favorites,
                        inCart = data.in_cart
                    )
                }

                binding.apply {
                    welcomeText.visibility = if (state.data != null) View.VISIBLE else View.GONE
                    banner.visibility = if (state.data != null) View.VISIBLE else View.GONE
                    appTitle.visibility = if (state.data != null) View.VISIBLE else View.GONE
                    recyclerView.visibility = if (state.data != null) View.VISIBLE else View.GONE
                    errorMessageText.visibility = if (state.errorMessage != "") View.VISIBLE else View.GONE
                    tryAgainButton.visibility = if (state.errorMessage != "") View.VISIBLE else View.GONE
                    progressBar.visibility = if (state.isLoading == true) View.VISIBLE else View.GONE
                }

                if (state.data != null) {
                    genericAdapter.setData(homeDataList !!)
                    showBanners()
                } else if (state.errorMessage != "") {
                        binding.errorMessageText.text = state.errorMessage
                        binding.tryAgainButton.visibility = View.VISIBLE
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            cartViewModel.addRemoveState.collect { addRemoveState ->
                if (addRemoveState.data != null) {
                    Toast.makeText(requireContext(),addRemoveState.data.message, Toast.LENGTH_LONG).show()
                } else if (addRemoveState.errorMessage != "") {
                    Toast.makeText(requireContext(),addRemoveState.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            favoritesViewModel.addRemoveUIState.collect { addRemoveState ->
                if (addRemoveState.data != null) {
                    Toast.makeText(requireContext(),addRemoveState.data.message, Toast.LENGTH_LONG).show()
                } else if (addRemoveState.errorMessage != "") {
                    Toast.makeText(requireContext(),addRemoveState.errorMessage, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private suspend fun showBanners() {
        while (true) {
            for (banner in homeViewModel.state.value.data?.data?.banners !!) {
                Glide.with(binding.banner).load(banner.image).into(binding.banner)
                delay(3000)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
