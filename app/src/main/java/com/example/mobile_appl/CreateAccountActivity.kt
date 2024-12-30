package com.example.mobile_appl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    private val etFullName: EditText by lazy { findViewById(R.id.etFullName) }
    private val etEmail: EditText by lazy { findViewById(R.id.etEmail) }
    private val etPhoneNumber: EditText by lazy { findViewById(R.id.etPhoneNumber) }
    private val etPassword: EditText by lazy { findViewById(R.id.etPassword) }
    private val cbTerms: CheckBox by lazy { findViewById(R.id.cbTerms) }
    private val btnNext: Button by lazy { findViewById(R.id.btnNext) }
    private val tvLogIn: TextView by lazy { findViewById(R.id.tvLogIn) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        credentialsManager = CredentialsManager()

        btnNext.setOnClickListener {
            val fullName = etFullName.text.toString()
            val email = etEmail.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val password = etPassword.text.toString()

            if (fullName.isNotEmpty() && email.isNotEmpty() && phoneNumber.isNotEmpty() && password.isNotEmpty() && cbTerms.isChecked) {
                if (credentialsManager.register(email, password)) {
                    Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                    // Navigate back to Sign In screen
                    val intent = Intent(this, SignInActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Email is already taken", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields and accept the terms", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogIn.setOnClickListener {
            // Navigate to Sign In screen and clear the current activity from the backstack
            val intent = Intent(this, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun handleRegistration() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (!credentialsManager.isEmailValid(email)) {
            etEmail.error = "Invalid email address"
            return
        }

        if (!credentialsManager.isPasswordValid(password)) {
            etPassword.error = "Password must be at least 4 characters"
            return
        }

        if (credentialsManager.register(email, password)) {
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
            finish() // Go back to LoginActivity
        } else {
            etEmail.error = "Email is already registered"
        }
    }
}
