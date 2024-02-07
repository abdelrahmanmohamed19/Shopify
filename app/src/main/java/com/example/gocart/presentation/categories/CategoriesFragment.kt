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
        val navController = requireActivity().findNavController(R.id.fragment_container_view)
        binding.apply {
            clothesCard.setOnClickListener { navController.navigate(R.id.clothesFragment) }
            SportsCard.setOnClickListener { navController.navigate(R.id.sportsFragment) }
            ElectronicsCard.setOnClickListener { navController.navigate(R.id.electronicsFragment) }
            LightingCard.setOnClickListener { navController.navigate(R.id.lightningFragment) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}