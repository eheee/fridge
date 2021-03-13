package com.heee.fridgetube.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import com.heee.fridgetube.R
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.data.RecipeCard
import com.heee.fridgetube.databinding.RecyclerViewRecipeBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoListAdapter(
    val itemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {
    private var recipeCards = listOf<RecipeCard>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipeCard = recipeCards[position]
        holder.binding.root.setOnClickListener {
            itemClickListener.onItemClicked(recipeCard)
        }


        holder.binding.ivThumbnail.load("https://img.youtube.com/vi/${recipeCard.recipe.videoId}/hqdefault.jpg")    //TODO Fill the imageView or Use 'mqdefault.jpg' support 16:9 size.
        {
            scale(Scale.FILL)
        }

        val inFridge = recipeCard.inFridge.map { it.name }
        val notInFridge = recipeCard.notInFridge.map { it.name }

        holder.binding.tvVideoTitle.text = "${recipeCard.recipe.name}"
        holder.binding.tvItemInFridge.text = "있는 재료 : $inFridge"
        holder.binding.tvItemNotInFridge.text = "없는 재료 : $notInFridge"
    }

    override fun getItemCount(): Int = recipeCards.size

    fun setRecipes(recipeCards: List<RecipeCard>) {
        this.recipeCards = recipeCards
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(recipeCard: RecipeCard)
    }

    class ViewHolder(
        val binding: RecyclerViewRecipeBinding
    ) :
        RecyclerView.ViewHolder(binding.root)

}