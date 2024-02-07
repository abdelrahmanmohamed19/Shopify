package com.example.gocart.presentation.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gocart.databinding.FragmentFavoritesBinding
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.presentation.common.adapter.GenericAdapter
import com.example.gocart.presentation.cart.CartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding !!
    private val favoritesViewModel by viewModels<FavoritesViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private val genericAdapter by lazy { GenericAdapter(requireContext(), favoritesViewModel, cartViewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = AppPreferences(requireContext()).getToken() !!
        binding.recyclerView.adapter = genericAdapter
        favoritesViewModel.getFavoritesList(token)

        binding.apply {
            emptyListText.visibility = View.GONE
            emptyListIcon.visibility = View.GONE
            errorMessageText.visibility = View.GONE
            tryAgainButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }

        lifecycleScope.launch {
            favoritesViewModel.favoritesUIState.collect{
                binding.apply {
                    recyclerView.visibility = if (it.data?.data?.data != null && it.data.data.data.isNotEmpty()) View.VISIBLE else View.GONE
                    errorMessageText.visibility = if (it.errorMessage != "") View.VISIBLE else View.GONE
                    tryAgainButton.visibility = if (it.errorMessage != "") View.VISIBLE else View.GONE
                    emptyListText.visibility = if (it.data?.data?.data != null && it.data.data.data.isEmpty()) View.VISIBLE else View.GONE
                    emptyListIcon.visibility = if (it.data?.data?.data != null && it.data.data.data.isEmpty()) View.VISIBLE else View.GONE
                    progressBar.visibility = if (it.isLoading == true) View.VISIBLE else View.GONE
                }

                if (it.data?.data?.data != null) {
                    val data = it.data.data.data.map {
                        ProductItems(
                            id = it.product.id.toString(),
                            image = it.product.image,
                            name = it.product.name,
                            oldPrice = it.product.old_price.toString(),
                            newPrice = it.product.price.toString(),
                            description = it.product.description,
                            isFavorite = true,
                            inCart = it.product.in_cart
                        )
                    }
                    genericAdapter.setData(data)
                } else if (it.errorMessage != "") {
                    binding.errorMessageText.text = it.errorMessage
                    binding.tryAgainButton.setOnClickListener { favoritesViewModel.getFavoritesList(token) }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}