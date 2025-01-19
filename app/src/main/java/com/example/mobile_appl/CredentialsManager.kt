package com.example.mobile_appl

import java.util.regex.Pattern

object CredentialsManager {
    private val EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    private val accounts: MutableMap<String, String> = mutableMapOf()

    fun isEmailValid(email: String): Boolean {
        return email.isNotEmpty() && EMAIL_PATTERN.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        if (accounts.containsKey(normalizedEmail)) {
            return false
        }
        accounts[normalizedEmail] = password
        return true
    }

    fun authenticate(email: String, password: String): Boolean {
        val normalizedEmail = email.lowercase()
        return accounts[normalizedEmail] == password
    }
}
