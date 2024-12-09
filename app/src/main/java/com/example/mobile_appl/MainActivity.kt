package com.example.mobile_appl

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private var showingFragmentA = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            // Initially display FragmentA
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentA())
                .commit()
        }

        val buttonToggle: Button = findViewById(R.id.button_toggle)
        buttonToggle.setOnClickListener {
            toggleFragment()
        }
    }

    private fun toggleFragment() {
        val fragment: Fragment = if (showingFragmentA) {
            FragmentB()
        } else {
            FragmentA()
        }
        showingFragmentA = !showingFragmentA
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
