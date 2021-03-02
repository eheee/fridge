package com.heee.fridgetube.ui.item

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.databinding.RecyclerViewItemBinding

class ItemsAdapter: RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    private var list = listOf<Item>()

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItemName.text = list[position].name
        holder.binding.root.setOnClickListener {
            onItemClickListener?.setItemClickListenr(list[position])
        }
    }

    override fun getItemCount() = list.size

    fun setItemList(items: List<Item>) {
        list = items
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun setItemClickListenr(item: Item)
    }

    class ViewHolder(val binding: RecyclerViewItemBinding) :RecyclerView.ViewHolder(binding.root) {

    }

}