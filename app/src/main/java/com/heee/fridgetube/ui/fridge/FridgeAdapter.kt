package com.heee.fridgetube.ui.fridge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.data.Cabinet
import com.heee.fridgetube.data.CabinetAndItem
import com.heee.fridgetube.databinding.RecyclerViewCabinetBinding

class FridgeAdapter : RecyclerView.Adapter<FridgeAdapter.ViewHolder>() {

    private var list = listOf<CabinetAndItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewCabinetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvItemName.text = list[position].item.name
    }

    override fun getItemCount(): Int = list.size

    fun setList(cabinets: List<CabinetAndItem>) {
        list = cabinets
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: RecyclerViewCabinetBinding): RecyclerView.ViewHolder(binding.root)
}