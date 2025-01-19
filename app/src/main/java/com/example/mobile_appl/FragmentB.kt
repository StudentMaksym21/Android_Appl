package com.example.mobile_appl

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_appl.databinding.FragmentSignInBinding

class FragmentB : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private lateinit var credentialsManager: CredentialsManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        credentialsManager = CredentialsManager

        binding.btnNext.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!credentialsManager.isEmailValid(email)) {
                binding.tilEmail.error = "Invalid email"
            } else {
                binding.tilEmail.error = null
            }

            if (!credentialsManager.isPasswordValid(password)) {
                binding.tilPassword.error = "Password cannot be empty"
            } else {
                binding.tilPassword.error = null
            }

            if (credentialsManager.authenticate(email, password)) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            } else {
                Toast.makeText(context, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvRegister.setOnClickListener {
            (activity as MainActivity).navigateToCreateAccount()
        }

        return binding.root
    }

    private fun handleLogin() {
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()

        if (!credentialsManager.isEmailValid(email)) {
            binding.etEmail.error = "Invalid email address"
            return
        }

        if (!credentialsManager.isPasswordValid(password)) {
            binding.etPassword.error = "Invalid password"
            return
        }

        if (credentialsManager.authenticate(email, password)) {
            startActivity(Intent(context, MainActivity::class.java))
        } else {
            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
