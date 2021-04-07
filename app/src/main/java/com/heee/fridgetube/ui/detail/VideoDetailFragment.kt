package com.heee.fridgetube.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.heee.fridgetube.R
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.data.entity.Library
import com.heee.fridgetube.data.entity.Memo
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
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentVideoDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var counterTop = arguments?.get("counterTop") as CounterTop
        val inFridge = counterTop.inFridge.map {
            it.name
        }
        binding.tvVideoTitle.text = counterTop.recipe.name
        if (counterTop.inFridge.isNotEmpty()) {
            binding.tvItemInFridge.text = counterTop.inFridge.asSequence()
                .map { it.name }
                .joinToString(", ")
        }

        for (item in counterTop.notInFridge) {
            val btn = Button(requireContext())
            btn.text = item.name
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(2, 2, 2, 2) }

            btn.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
            btn.isClickable = true
            btn.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
            btn.layoutParams = layoutParams
            btn.setOnClickListener {
                memoViewModel.addMemo(Memo(comment = item.name))
            }
            binding.llNotInFridgeContainer.addView(btn)
        }

        val youtubePlayerView: YouTubePlayerView =
            view.findViewById<YouTubePlayerView>(R.id.detail_youtube_player_view)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youtubePlayer: YouTubePlayer) { // Called when the player is ready to play video
                youtubePlayer.loadVideo(counterTop.recipe.videoId, 0F)
            }
        })

        binding.btnWatchLater.setOnClickListener {
            libraryVIewMemo.addLibrary(Library(counterTop.recipe.videoId))
            Snackbar.make(binding.llContainer,
                "보관함에 저장되었습니다.",
                Snackbar.LENGTH_LONG).show()
        }
    }

}