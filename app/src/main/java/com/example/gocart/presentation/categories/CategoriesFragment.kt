package com.example.gocart.presentation.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Categories"
        val navController = requireActivity().findNavController(R.id.fragment_container_view_main)
        binding.ElectronicsCard.setOnClickListener { navController.navigate(R.id.electronicsFragment) }
        binding.LightingCard.setOnClickListener { navController.navigate(R.id.lightningFragment) }
        binding.SportsCard.setOnClickListener { navController.navigate(R.id.sportsFragment) }
        binding.ClothesCard.setOnClickListener { navController.navigate(R.id.clothesFragment) }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}