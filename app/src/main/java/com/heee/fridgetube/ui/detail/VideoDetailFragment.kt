package com.heee.fridgetube.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.heee.fridgetube.R
import com.heee.fridgetube.data.Library
import com.heee.fridgetube.data.Memo
import com.heee.fridgetube.data.RecipeCard
import com.heee.fridgetube.databinding.FragmentVideoDetailBinding
import com.heee.fridgetube.ui.library.LibraryViewModel
import com.heee.fridgetube.ui.memo.MemoViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoDetailFragment : Fragment() {
    lateinit var binding: FragmentVideoDetailBinding

    private val viewModel: VideoDetailViewModel by viewModels()
    private val memoViewModel: MemoViewModel by viewModels()
    private val libraryVIewMemo: LibraryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var recipeCard = arguments?.get("recipeCard") as RecipeCard
        val inFridge = recipeCard.inFridge.map {
            it.name
        }
        binding.detailVideoTitle.text = recipeCard.recipe.name
        binding.tvDetailItemInFridge.text = inFridge.toString()

        for(item in recipeCard.notInFridge) {
            val textView = TextView(context)
            textView.text = item.name
            textView.setOnClickListener {
                memoViewModel.addMemo(Memo(comment = item.name))
            }
            binding.root.addView(textView)
        }

        val youtubePlayerView: YouTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.detail_youtube_player_view)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youtubePlayer: YouTubePlayer) { // Called when the player is ready to play video
                youtubePlayer.loadVideo(recipeCard.recipe.videoId, 0F)
            }
        })

        binding.btnWatchLater.setOnClickListener {
            libraryVIewMemo.addLibrary(Library(recipeCard.recipe.videoId))
        }
    }

}