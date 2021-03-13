package com.heee.fridgetube.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.databinding.RecyclerViewRecipeBinding

class VideoListAdapter(
    val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {
    private var counterTopList = listOf<CounterTop>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val counterTop = counterTopList[position]
        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClicked(counterTop)
        }


        holder.binding.ivThumbnail.load("https://img.youtube.com/vi/${counterTop.recipe.videoId}/hqdefault.jpg")    //TODO Fill the imageView or Use 'mqdefault.jpg' support 16:9 size.
        {
            scale(Scale.FILL)
        }

        val inFridge = counterTop.inFridge.map { it.name }
        val notInFridge = counterTop.notInFridge.map { it.name }

        holder.binding.tvVideoTitle.text = "${counterTop.recipe.name}"
        holder.binding.tvItemInFridge.text = "있는 재료 : $inFridge"
        holder.binding.tvItemNotInFridge.text = "없는 재료 : $notInFridge"
    }

    override fun getItemCount(): Int = counterTopList.size

    fun setRecipes(counterTops: List<CounterTop>) {
        this.counterTopList = counterTops
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(counterTop: CounterTop)
    }

    class ViewHolder(
        val binding: RecyclerViewRecipeBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

}