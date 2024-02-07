package com.example.gocart.presentation.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gocart.databinding.FragmentCartBinding
import com.example.gocart.data.local.SharedPreferences
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
        requireActivity().title = "Cart"
        val token = SharedPreferences(requireContext()).fetchToken()!!
        viewModel.getCartList(token, requireContext(), view)
        lifecycleScope.launch {
            viewModel.apply {
                isLoading.collect{
                    when (it) {
                        true -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.INVISIBLE
                            binding.emptyListText.visibility = View.INVISIBLE
                            binding.placeOrderButton.visibility = View.INVISIBLE
                        }
                        false -> {
                            isSuccess.collect{
                                when(it) {
                                    true -> {
                                        cartList.collect{
                                            if (it.isNotEmpty()) {
                                                binding.progressBar.visibility = View.INVISIBLE
                                                binding.recyclerView.visibility = View.VISIBLE
                                                binding.emptyListText.visibility = View.INVISIBLE
                                                binding.placeOrderButton.visibility = View.VISIBLE
                                                binding.totalPrice.text = this.totalPrice
                                                cartAdapter.setData(it)

                                            } else {
                                                binding.progressBar.visibility = View.INVISIBLE
                                                binding.recyclerView.visibility = View.INVISIBLE
                                                binding.emptyListText.visibility = View.VISIBLE
                                                binding.placeOrderButton.visibility = View.INVISIBLE
                                            }
                                        }
                                    }
                                    false -> {
                                        binding.progressBar.visibility = View.INVISIBLE
                                        binding.recyclerView.visibility = View.INVISIBLE
                                        binding.emptyListText.visibility = View.INVISIBLE
                                        binding.placeOrderButton.visibility = View.INVISIBLE
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