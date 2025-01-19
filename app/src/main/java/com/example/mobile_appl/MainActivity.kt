package com.example.mobile_appl

import Item
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ItemAdapter(getSampleItems(), ::onItemClick)
        recyclerView.adapter = adapter
    }

    private fun onItemClick(item: Item) {
        Toast.makeText(this, "Item clicked: ${item.id}", Toast.LENGTH_SHORT).show()
        Log.d("Item Clicked", "Item ID: ${item.id}")
    }

    private fun getSampleItems(): List<Item> {
        return listOf(
            Item(1, R.drawable.image1, "Title 1", "Description 1"),
            Item(2, R.drawable.image2, "Title 2", "Description 2")
        )
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
