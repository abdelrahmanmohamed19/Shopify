package com.example.gocart.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.data.local.SharedPreferences
import com.example.gocart.databinding.FragmentDisplayBinding

class DisplayFragment : Fragment() {

    private var _binding: FragmentDisplayBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDisplayBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_container_view) as? NavHostFragment
        val navController = navHostFragment!!.navController
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> navController.navigate(R.id.homeFragment)
                R.id.categories -> navController.navigate(R.id.categoriesFragment)
                R.id.cart -> navController.navigate(R.id.cartFragment)
                R.id.favorites -> navController.navigate(R.id.favoritesFragment)
            }
            true
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Logout -> {
                SharedPreferences(requireContext()).apply {
                    clearToken()
                    saveLoginStatus(0)
                    findNavController().navigate(R.id.action_displayFragment_to_signInFragment)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
