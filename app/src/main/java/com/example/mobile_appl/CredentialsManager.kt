package com.example.mobile_appl

import java.util.regex.Pattern

class CredentialsManager {

    // Custom email pattern
    private val EMAIL_PATTERN = Pattern.compile(
        "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
    )

    private val accounts: MutableMap<String, String> = mutableMapOf()

    fun isEmailValid(email: String): Boolean {
        return email.isNotEmpty() && EMAIL_PATTERN.matcher(email).matches()
    }

    fun isPasswordValid(password: String): Boolean {
        return password.isNotEmpty()
    }

    fun register(email: String, password: String): Boolean {
        if (accounts.containsKey(email)) {
            return false
        }
        accounts[email] = password
        return true
    }
}
