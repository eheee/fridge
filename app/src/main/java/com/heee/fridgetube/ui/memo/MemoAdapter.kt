package com.heee.fridgetube.ui.memo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textview.MaterialTextView
import com.heee.fridgetube.data.entity.Memo
import com.heee.fridgetube.databinding.RecyclerViewMemoBinding

class MemoAdapter : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    private var memos = mapOf<String, List<Memo>>()
    var onItemClinkListener: OnItemClinkListener? = null

    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewMemoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context;
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keys = memos.keys
        val date = keys.elementAt(position)

        if(memos[date] == null) return

        holder.binding.tvMemoDate.text = memos[date]!![0].inputDate
        for(memo in memos[date]!!) {
            holder.binding.llMemos.addView(MaterialTextView(context!!).apply {
                text = memo.comment
                setPadding(5,5,5,5)
            })
        }

        holder.binding.ivDelete.setOnClickListener {
            onItemClinkListener?.itemClick(memos[date]!!)
        }

    }

    override fun getItemCount() = memos.size

    fun setMemoList(memos: Map<String, List<Memo>>) {
        this.memos = memos
        notifyDataSetChanged()
    }

    interface OnItemClinkListener {
        fun itemClick(memos: List<Memo>)
    }

    class ViewHolder(val binding: RecyclerViewMemoBinding) : RecyclerView.ViewHolder(binding.root)


}