package com.seven.setest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class GoodsAdapter(private val context: Context, private val listener: GoodListener) :
    ListAdapter<Good, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<Good>() {
        override fun areItemsTheSame(oldItem: Good, newItem: Good): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Good, newItem: Good): Boolean {
            return oldItem.name == newItem.name
        }
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_good, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            val good = getItem(position)
            holder.name.text = good.name
            Glide.with(context).load(good.imageUrl).into(holder.image)
            holder.itemView.setOnClickListener {
                listener.onClick(good)
            }
        }
    }

    internal class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.tv_name)
        var image: ImageView = itemView.findViewById(R.id.iv_image)
    }

}

class GoodListener(val clickListener: (order: Good) -> Unit) {
    fun onClick(good: Good) {
        clickListener(good)
    }
}
