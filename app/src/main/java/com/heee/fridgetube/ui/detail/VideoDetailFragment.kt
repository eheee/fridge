package com.heee.fridgetube.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.heee.fridgetube.R
import com.heee.fridgetube.data.Item
import com.heee.fridgetube.data.RecipeCard
import com.heee.fridgetube.databinding.FragmentVideoDetailBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoDetailFragment : Fragment() {
    lateinit var binding: FragmentVideoDetailBinding

    private lateinit var viewModel: VideoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideoDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recipeCard = arguments?.get("recipeCard") as RecipeCard
        val inFridge = recipeCard.inFridge.map {
            it.name
        }
        binding.detailVideoTitle.text = recipeCard.recipe.name
        binding.tvDetailItemInFridge.text = inFridge.toString()

        val youtubePlayerView: YouTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.detail_youtube_player_view)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youtubePlayer: YouTubePlayer) { // Called when the player is ready to play video
                youtubePlayer.loadVideo(recipeCard.recipe.videoId, 0F)
            }
        })
    }

}