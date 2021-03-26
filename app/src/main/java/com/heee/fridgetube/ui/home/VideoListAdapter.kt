package com.heee.fridgetube.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.databinding.RecyclerViewRecipeBinding
import com.heee.fridgetube.ui.utils.toRoundedShape

class VideoListAdapter(
    val itemClickListener: OnItemClickListener,
) :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {
    private var counterTopList = listOf<CounterTop>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        binding.tvTag.toRoundedShape()

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val counterTop = counterTopList[position]
        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClicked(counterTop)
        }

        holder.binding.ivThumbnail.load(YOUTUBE_IMG_URI + counterTop.recipe.videoId + HIGH_QUALITY_IMG)    //TODO Fill the imageView or Use 'mqdefault.jpg' support 16:9 size.
        {
            scale(Scale.FILL)
        }

        val inFridge = counterTop.inFridge.asSequence().map { it.name }.joinToString(", ")
        val notInFridge = counterTop.notInFridge.asSequence().map { it.name }.joinToString(", ")

        holder.binding.tvVideoTitle.text = "${counterTop.recipe.name}"
        holder.binding.tvItemInFridge.text = "$inFridge"
        holder.binding.tvItemNotInFridge.text = "$notInFridge"
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
        val binding: RecyclerViewRecipeBinding,
    ) :
        RecyclerView.ViewHolder(binding.root)

    companion object {
        const val YOUTUBE_IMG_URI = "https://img.youtube.com/vi/"
        const val HIGH_QUALITY_IMG = "/hqdefault.jpg"
    }
}