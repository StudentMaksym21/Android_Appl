package com.example.mobile_appl

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout

class SignInActivity : AppCompatActivity() {

    private lateinit var credentialsManager: CredentialsManager

    private val tilEmail: TextInputLayout by lazy { findViewById(R.id.tilEmail) }
    private val etEmail: EditText by lazy { findViewById(R.id.etEmail) }
    private val tilPassword: TextInputLayout by lazy { findViewById(R.id.tilPassword) }
    private val etPassword: EditText by lazy { findViewById(R.id.etPassword) }
    private val cbRememberMe: CheckBox by lazy { findViewById(R.id.cbRememberMe) }
    private val btnNext: Button by lazy { findViewById(R.id.btnNext) }
    private val tvRegister: TextView by lazy { findViewById(R.id.tvRegister) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        credentialsManager = CredentialsManager()

        btnNext.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (!credentialsManager.isEmailValid(email)) {
                tilEmail.error = "Invalid email"
            } else {
                tilEmail.error = null
            }

            if (!credentialsManager.isPasswordValid(password)) {
                tilPassword.error = "Password cannot be empty"
            } else {
                tilPassword.error = null
            }

            if (email == "test@te.st" && password == "1234") {
                // Navigate to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else if (credentialsManager.isEmailValid(email) && credentialsManager.isPasswordValid(password)) {
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegister.setOnClickListener {
            // Navigate to Create Account screen and clear the current activity from the backstack
            val intent = Intent(this, CreateAccountActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }
    }

    private fun handleLogin() {
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()

        if (!credentialsManager.isEmailValid(email)) {
            etEmail.error = "Invalid email address"
            return
        }

        if (!credentialsManager.isPasswordValid(password)) {
            etPassword.error = "Invalid password"
            return
        }

        if (credentialsManager.authenticate(email, password)) {
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
        }
    }
}
