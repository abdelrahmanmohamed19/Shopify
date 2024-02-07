package com.example.gocart.presentation.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding : FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<SignupViewModel>()
    private lateinit var navController : NavController
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Shopify"
        navController = findNavController()
        binding.signupButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val phoneNumber = binding.phoneNumberInput.text.toString()
            viewModel.signUp(requireContext(), email, password, username, phoneNumber , navController)
        }

        binding.loginText.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}