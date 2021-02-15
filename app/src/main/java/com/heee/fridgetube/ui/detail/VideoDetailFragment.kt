package com.heee.fridgetube.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.heee.fridgetube.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoDetailFragment : Fragment() {

    companion object {
        fun newInstance() = VideoDetailFragment()
    }

    private lateinit var viewModel: VideoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(VideoDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var videoId = arguments?.getString("videoId")
        Toast.makeText(requireContext(), "$videoId", Toast.LENGTH_SHORT).show()

        val youtubePlayerView: YouTubePlayerView = view.findViewById<YouTubePlayerView>(R.id.detail_youtube_player_view)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youtubePlayer: YouTubePlayer) { // Called when the player is ready to play video
                youtubePlayer?.loadVideo(videoId!!, 0F)
            }
        })
    }

}