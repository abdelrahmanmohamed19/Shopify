package com.example.gocart.presentation.signUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment: Fragment() {

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

        navController = findNavController()
        binding.apply {
            loadingProgressBar.visibility = View.GONE
            signupButtonText.visibility = View.VISIBLE
        }

        binding.signupButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            val username = binding.usernameInput.text.toString()
            val phoneNumber = binding.phoneNumberInput.text.toString()
            viewModel.signUp(requireContext(), email, password, username, phoneNumber)
            lifecycleScope.launch {
                viewModel.state.collect {
                    if (it.data?.status == true) {
                        binding.apply {
                            loadingProgressBar.visibility = View.GONE
                            signupButtonText.visibility = View.VISIBLE
                        }
                        Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG).show()
                        navController.navigate(R.id.action_signUpFragment_to_mainFragment)
                    } else if (it.data?.status == false) {
                        binding.apply {
                            loadingProgressBar.visibility = View.GONE
                            signupButtonText.visibility = View.VISIBLE
                        }
                        Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG).show()
                    } else if (it.errorMessage != "") {
                        binding.apply {
                            loadingProgressBar.visibility = View.GONE
                            signupButtonText.visibility = View.VISIBLE
                        }
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    } else if (it.isLoading == true) {
                        binding.apply {
                            loadingProgressBar.visibility = View.VISIBLE
                            signupButtonText.visibility = View.GONE
                        }
                    }
                }
            }
        }

        binding.loginText.setOnClickListener {
            navController.navigate(R.id.action_signUpFragment_to_loginFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}