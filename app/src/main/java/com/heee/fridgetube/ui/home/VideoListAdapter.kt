package com.heee.fridgetube.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoListAdapter(private val videoList: Array<String>, private val lifecycle: Lifecycle) :
    RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)

        val youtubePlayerView: YouTubePlayerView = itemView.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
        lifecycle.addObserver(youtubePlayerView)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            println("$position 클릭")
            itemClickListener?.onItemClicked(position, videoList[position])
        }
        holder.readyVideo(videoList[position])
    }

    override fun getItemCount(): Int = videoList.size

    interface OnItemClickListener {
        fun onItemClicked(position: Int, videoId: String)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var youtubePlayer: YouTubePlayer? = null
        private var currentVideoId: String? = null

        init {
            val youtubePlayerView: YouTubePlayerView = itemView.findViewById<YouTubePlayerView>(R.id.youtube_player_view)
            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
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