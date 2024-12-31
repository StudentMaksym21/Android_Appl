package com.example.mobile_appl

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_appl.databinding.FragmentCreateAccountBinding

class FragmentA : Fragment() {

    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        credentialsManager = CredentialsManager

        binding.btnNext.setOnClickListener {
            val fullName = binding.etFullName.text.toString()
            val email = binding.etEmail.text.toString()
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

            if (fullName.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() && password.isNotEmpty() && binding.cbTerms.isChecked) {
                if (credentialsManager.register(email, password)) {
                    Toast.makeText(context, "Account created successfully", Toast.LENGTH_SHORT).show()
                    (activity as MainActivity).navigateToSignIn()
                } else {
                    Toast.makeText(context, "Email is already taken", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Please fill all fields and accept the terms", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogIn.setOnClickListener {
            (activity as MainActivity).navigateToSignIn()
        }

        return binding.root
    }

    private fun handleRegistration() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (!credentialsManager.isEmailValid(email)) {
            binding.etEmail.error = "Invalid email address"
            return
        }

        if (!credentialsManager.isPasswordValid(password)) {
            binding.etPassword.error = "Password must be at least 4 characters"
            return
        }

        if (credentialsManager.register(email, password)) {
            Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
            activity?.finish() // Go back to SignInFragment
        } else {
            binding.etEmail.error = "Email is already registered"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
