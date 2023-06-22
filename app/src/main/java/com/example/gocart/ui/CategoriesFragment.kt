package com.example.gocart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    lateinit var binding : FragmentCategoriesBinding
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCategoriesBinding.inflate(layoutInflater)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ElectronicDevices.setOnClickListener { navController.navigate(R.id.action_displayFragment_to_electrionicsFragment) }
        binding.Lighting.setOnClickListener { navController.navigate(R.id.action_displayFragment_to_lightingFragment) }
        binding.Sports.setOnClickListener { navController.navigate(R.id.action_displayFragment_to_sportsFragment) }
        binding.Clothes.setOnClickListener { navController.navigate(R.id.action_displayFragment_to_clothesFragment) }
    }

}