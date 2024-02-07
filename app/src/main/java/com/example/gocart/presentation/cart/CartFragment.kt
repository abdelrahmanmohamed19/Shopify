package com.example.gocart.presentation.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gocart.databinding.FragmentCartBinding
import com.example.gocart.data.local.AppPreferences
import com.example.gocart.domain.model.CartItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CartViewModel>()
    private val cartAdapter by lazy { CartAdapter(requireContext(), viewModel) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCartBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = cartAdapter
        val token = AppPreferences(requireContext()).getToken() ?: ""
        viewModel.getCartList(token)

        binding.apply {
            progressBar.visibility = View.GONE
            emptyListText.visibility = View.GONE
            emptyListIcon.visibility = View.GONE
            errorMessageText.visibility = View.GONE
            recyclerView.visibility = View.GONE
            tryAgainButton.visibility = View.GONE
            placeOrderButton.visibility = View.GONE
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect{ state ->
                binding.apply {
                    recyclerView.visibility = if (state.data?.data != null && state.data.data.cartItems.isNotEmpty()) View.VISIBLE else View.GONE
                    emptyListIcon.visibility = if (state.data?.data != null && state.data.data.cartItems.isEmpty()) View.VISIBLE else View.GONE
                    emptyListText.visibility = if (state.data?.data != null && state.data.data.cartItems.isEmpty()) View.VISIBLE else View.GONE
                    errorMessageText.visibility = if (state.errorMessage != "") View.VISIBLE else View.GONE
                    progressBar.visibility = if (state.isLoading == true) View.VISIBLE else View.GONE
                    tryAgainButton.visibility = if (state.errorMessage != "") View.VISIBLE else View.GONE
                    placeOrderButton.visibility = if (state.data?.data != null && state.data.data.cartItems.isNotEmpty()) View.VISIBLE else View.GONE
                }

                if (state.data?.data != null) {
                    if (state.data.data.cartItems.isNotEmpty()) {
                        val data = state.data.data.cartItems.map {
                            CartItem(
                                id = it.product.id,
                                name = it.product.name,
                                quantity = it.quantity.toString(),
                                price = it.product.price.toString()
                            )
                        }
                        cartAdapter.setData(data)
                        binding.totalPrice.text ="${state.data.data.total.toString()} EG"
                    }
                } else if (state.errorMessage != "") {
                    binding.emptyListText.text = state.errorMessage
                    binding.tryAgainButton.setOnClickListener { viewModel.getCartList(token) }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}