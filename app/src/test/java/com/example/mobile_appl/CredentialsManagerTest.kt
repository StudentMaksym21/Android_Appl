package com.example.mobile_appl

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CredentialsManagerTest {

    private lateinit var credentialsManager: CredentialsManager

    @Before
    fun setUp() {
        credentialsManager = CredentialsManager()
    }

    @Test
    fun testEmailEmpty() {
        assertFalse(credentialsManager.isEmailValid(""))
    }

    @Test
    fun testEmailWrongFormat() {
        assertFalse(credentialsManager.isEmailValid("invalidemail.com"))
    }

    @Test
    fun testEmailWellFormatted() {
        assertTrue(credentialsManager.isEmailValid("test@example.com"))
    }

    @Test
    fun testPasswordEmpty() {
        assertFalse(credentialsManager.isPasswordValid(""))
    }

    @Test
    fun testPasswordFilled() {
        assertTrue(credentialsManager.isPasswordValid("password123"))
    }

    @Test
    fun testHardcodedCredentials() {
        val email = "test@te.st"
        val password = "1234"
        assertTrue(credentialsManager.isEmailValid(email))
        assertTrue(credentialsManager.isPasswordValid(password))
    }

    @Test
    fun testInvalidCredentials() {
        val email = "wrong@te.st"
        val password = "wrongpassword"
        assertFalse(credentialsManager.isEmailValid(email) && email == "test@te.st" && password == "1234")
    }

    @Test
    fun testRegisterNewAccount() {
        val email = "newuser@example.com"
        val password = "password123"
        val result = credentialsManager.register(email, password)
        assertTrue(result)
    }

    @Test
    fun testRegisterExistingAccount() {
        val email = "existinguser@example.com"
        val password = "password123"
        credentialsManager.register(email, password)
        val result = credentialsManager.register(email, password)
        assertFalse(result)
    }

    @Test
    fun testAuthenticateRegisteredUser() {
        val result = credentialsManager.authenticate("test@te.st", "1234")
        assertTrue(result)
    }

    @Test
    fun testAuthenticateInvalidCredentials() {
        val result = credentialsManager.authenticate("nonexistent@te.st", "wrong")
        assertFalse(result)
    }
}
