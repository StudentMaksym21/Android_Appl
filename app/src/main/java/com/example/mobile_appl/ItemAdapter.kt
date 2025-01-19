package com.example.mobile_appl

import Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_appl.databinding.ItemListBinding

class ItemAdapter(private val items: List<Item>, private val itemClickListener: (Item) -> Unit) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item) {
            itemView.findViewById<TextView>(R.id.itemTitle).text = item.title
            itemView.findViewById<TextView>(R.id.itemDescription).text = item.description
            itemView.findViewById<ImageView>(R.id.itemImage).setImageResource(item.imageId)
            itemView.findViewById<Button>(R.id.btnLike).setOnClickListener { itemClickListener(item) }
            itemView.findViewById<Button>(R.id.btnShare).setOnClickListener { itemClickListener(item) }
        }
    }
}
