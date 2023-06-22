package com.example.gocart.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentDisplayBinding
import com.example.gocart.model.SessionManager
import com.example.gocart.model.repositories.LogininRepository
import com.example.gocart.viewModel.LoginViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class DisplayFragment : Fragment() {

    lateinit var binding : FragmentDisplayBinding
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDisplayBinding.inflate(layoutInflater)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        bottomNavigation()
    }



    fun bottomNavigation(){
        binding.bottomnavi.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> { openFragment(HomeFragment()) }
                R.id.categories -> { openFragment(CategoriesFragment()) }
                R.id.cart -> { openFragment(CartFragment()) }
                R.id.favorites -> { openFragment(FavoritesFragment()) }
               }
            true
            }
        }

    private fun openFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.actionbar,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when (item.itemId){
            R.id.Logout -> {
                SessionManager(requireContext()).clearToken()
                Toast.makeText(requireContext(),"Logged out",Toast.LENGTH_SHORT).show()
                true
            }
           else -> return super.onOptionsItemSelected(item)
        }

    }
}