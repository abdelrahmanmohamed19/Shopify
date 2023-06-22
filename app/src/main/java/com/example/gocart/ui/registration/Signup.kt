package com.example.gocart.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.gocart.R
import com.example.gocart.databinding.FragmentSignupBinding
import com.example.gocart.viewModel.SignupViewModel


class Signup : Fragment() {

    lateinit var navController: NavController

    lateinit var binding: FragmentSignupBinding

    lateinit var viewModel : SignupViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        binding.lifecycleOwner = this
        navController=findNavController()


        binding.btnReg.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPass.text.toString()
            val name = binding.etConfPass.text.toString()
            val phoneNumber = binding.PhoneNumb.text.toString()
            val signup = viewModel.signUp(requireContext(),email,password,name,phoneNumber)
            signup
            signup.observe(viewLifecycleOwner) {success ->
                if (success){
                    navController.navigate(R.id.action_signup_to_displayFragment)
                }
            }
        }

        binding.textLog.setOnClickListener {
            navController.navigate(R.id.action_signup_to_login)
        }

    }
}