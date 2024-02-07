package com.example.gocart.presentation.login

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
import com.example.gocart.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment: Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding !!
    private lateinit var navController: NavController
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()
        binding.apply {
            buttonText.visibility = View.VISIBLE
            loadingProgressBar.visibility = View.GONE
        }

        binding.loginButton.setOnClickListener {
            val email = binding.emailInput.text.toString()
            val password = binding.passwordInput.text.toString()
            viewModel.login(email, password, requireContext())
            lifecycleScope.launch {
                viewModel.state.collect{
                    if (it.data?.status == true) {
                        binding.apply {
                            buttonText.visibility = View.VISIBLE
                            loadingProgressBar.visibility = View.GONE
                        }
                        Toast.makeText(requireContext(), it.data.message, Toast.LENGTH_LONG).show()
                        navController.navigate(R.id.action_loginFragment_to_mainFragment)
                    } else if (it.errorMessage != "") {
                        binding.apply {
                            buttonText.visibility = View.VISIBLE
                            loadingProgressBar.visibility = View.GONE
                        }
                        Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_LONG).show()
                    } else if (it.isLoading == true) {
                        binding.apply {
                            buttonText.visibility = View.GONE
                            loadingProgressBar.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }

        binding.createAccountButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}