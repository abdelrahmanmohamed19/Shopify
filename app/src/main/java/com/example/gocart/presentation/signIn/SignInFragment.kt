package com.example.gocart.presentation.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment  : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding !!
    private lateinit var navController: NavController
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
                return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Shopify"
        navController = findNavController()
        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.signIn(email, password, navController, requireContext())
        }

        binding.textReg.setOnClickListener {
            navController.navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}