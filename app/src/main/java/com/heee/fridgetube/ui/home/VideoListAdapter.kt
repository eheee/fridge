package com.heee.fridgetube.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.data.Recipe
import com.heee.fridgetube.databinding.RecyclerViewRecipeBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener

class VideoListAdapter(private val lifecycle: Lifecycle) :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {
    private var recipeList = listOf<Recipe>()

    var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RecyclerViewRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        lifecycle.addObserver(binding.youtubePlayerView)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClicked(position, recipeList[position].videoId)
        }
        holder.readyVideo(recipeList[position].videoId)
        //FIXME The title on video screen was changed instead of this textView.
        holder.binding.videoTitle.text = recipeList[position].name
    }

    override fun getItemCount(): Int = recipeList.size

    fun setRecipes(recipes: List<Recipe>) {
        recipeList = recipes
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClicked(position: Int, videoId: String)
    }

    class ViewHolder(val binding: RecyclerViewRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var youtubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        init {
            binding.youtubePlayerView.addYouTubePlayerListener(object :
                AbstractYouTubePlayerListener() {
                override fun onReady(initializedYouTubePlayer: YouTubePlayer) { // Called when the player is ready to play video
                    youtubePlayer = initializedYouTubePlayer
                    youtubePlayer?.cueVideo(currentVideoId!!, 0F)
                    youtubePlayer?.mute()
                }
            })
        }

        fun readyVideo(videoId: String) {
            currentVideoId = videoId
        }
    }

}