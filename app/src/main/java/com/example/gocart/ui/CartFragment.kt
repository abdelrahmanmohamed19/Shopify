package com.example.gocart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.gocart.R
import com.example.gocart.adapters.CartAdapter
import com.example.gocart.databinding.FragmentCartBinding
import com.example.gocart.model.SessionManager
import com.example.gocart.viewModel.CartViewModel


class CartFragment : Fragment() {

    lateinit var binding : FragmentCartBinding
    lateinit var viewModel : CartViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCartBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = SessionManager(requireContext()).fetchToken()
        viewModel.getCart(token.toString())
        val adapter = CartAdapter(requireContext(),viewModel)
        binding.FavoritesRecycler.adapter=adapter
        viewModel.cachedData.observe(viewLifecycleOwner){adapter.setData(it)}
        viewModel.cachedDatatotal.observe(viewLifecycleOwner){binding.totalprice.text = "Total Price: ${it.toString()} EG"}
    }


}