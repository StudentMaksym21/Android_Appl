package com.example.mobile_appl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentB())
                .commit()
        }
    }

    fun navigateToCreateAccount() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, FragmentA())
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun navigateToSignIn() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, FragmentB())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
