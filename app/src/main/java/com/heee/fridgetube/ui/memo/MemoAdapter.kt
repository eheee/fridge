package com.heee.fridgetube.ui.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.data.Memo
import com.heee.fridgetube.databinding.RecyclerViewMemoBinding

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    var list = listOf<Memo>()
    var onItemClinkListener: OnItemClinkListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvMemoComment.text = list[position].comment
        holder.binding.tvMemoComment.setOnClickListener {
            onItemClinkListener?.itemClick(list[position])
        }
    }

    override fun getItemCount() = list.size

    fun setMemoList(memos: List<Memo>) {
        list = memos
        notifyDataSetChanged()
    }

    interface OnItemClinkListener {
        fun itemClick(memo: Memo)
    }

    class ViewHolder(val binding: RecyclerViewMemoBinding) : RecyclerView.ViewHolder(binding.root)


}