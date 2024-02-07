package com.example.gocart.presentation.sports

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.presentation.common.adapter.GenericAdapter
import com.example.gocart.databinding.FragmentSportsBinding
import com.example.gocart.domain.model.ProductItems
import com.example.gocart.presentation.cart.CartViewModel
import com.example.gocart.presentation.favorites.FavoritesViewModel
import com.example.gocart.presentation.common.viewmodel.CategoryProductsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SportsFragment : Fragment() {

    private var _binding : FragmentSportsBinding? = null
    private val binding get() = _binding !!
    private val categoriesViewModel by viewModels<CategoryProductsViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()
    private val cartViewModel by viewModels<CartViewModel>()
    private val genericAdapter by lazy { GenericAdapter(requireContext(), favoritesViewModel, cartViewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSportsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = AppPreferences(requireContext()).getToken() ?: ""
        binding.recyclerView.adapter = genericAdapter

        binding.apply {
            title.visibility = View.GONE
            recyclerView.visibility = View.GONE
            errorMessageText.visibility = View.GONE
            tryAgainButton.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
        }

        categoriesViewModel.getCategoriesProducts(token, 42)

        viewLifecycleOwner.lifecycleScope.launch {
            categoriesViewModel.state.collect{

                binding.apply {
                    title.visibility = if (it.data != null) View.VISIBLE else View.GONE
                    recyclerView.visibility = if (it.data != null) View.VISIBLE else View.GONE
                    errorMessageText.visibility = if (it.errorMessage != "") View.VISIBLE else View.GONE
                    tryAgainButton.visibility = if (it.errorMessage != "") View.VISIBLE else View.GONE
                    progressBar.visibility = if (it.isLoading == true) View.VISIBLE else View.GONE
                }

                if (it.data != null) {
                    val data = it.data.data.data.map {
                        ProductItems(
                            id = it.id.toString(),
                            image = it.image,
                            name = it.name,
                            newPrice = it.price.toString(),
                            oldPrice = it.oldPrice.toString(),
                            description = it.description,
                            isFavorite = it.in_favorites,
                            inCart = it.in_cart
                        )
                    }
                    genericAdapter.setData(data)
                } else if (it.errorMessage != "") {
                    binding.errorMessageText.text = it.errorMessage
                    binding.tryAgainButton.setOnClickListener{categoriesViewModel.getCategoriesProducts(token, 42)}
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}