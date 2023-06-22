package com.example.gocart.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentLoginBinding
import com.example.gocart.model.SessionManager
import com.example.gocart.ui.DisplayFragment
import com.example.gocart.viewModel.LoginViewModel

class Login  : Fragment() {

    private lateinit var navController: NavController
    lateinit var viewModel : LoginViewModel
    lateinit var binding : FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.lifecycleOwner = this
        navController = findNavController()

        val token =SessionManager(requireContext()).fetchToken()

        if (token != null)
        {
            navController.navigate(R.id.action_login_to_displayFragment)
        }


        binding.bbtnLog.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()
            viewModel.login(requireContext(), email, password).observe(viewLifecycleOwner) { success ->
                if (success) {
                    navController.navigate(R.id.action_login_to_displayFragment)
                }
            }
        }

        binding.textReg.setOnClickListener {
            navController.navigate(R.id.action_login_to_signup)
        }
    }
}