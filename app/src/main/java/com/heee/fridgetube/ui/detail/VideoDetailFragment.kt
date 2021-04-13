package com.heee.fridgetube.ui.detail

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
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
    private val libraryViewMemo: LibraryViewModel by viewModels()

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

            val llItem = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                background = requireContext().getDrawable(R.drawable.shape_square_item_container)
            }


            val tvItemName = TextView(requireContext())
            tvItemName.text = item.name
            tvItemName.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 1.0f
                gravity = Gravity.START or Gravity.CENTER_VERTICAL
            }


            val ivAddMemo = ImageView(requireContext())
            ivAddMemo.setImageDrawable(requireContext().getDrawable(R.drawable.ic_sticky_note_2_black_24dp))
            ivAddMemo.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                weight = 0.1f
                gravity = Gravity.END or Gravity.CENTER_VERTICAL
            }
            ivAddMemo.setOnClickListener {
                memoViewModel.addMemo(Memo(comment = item.name))
                ivAddMemo.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_check_24))
                ivAddMemo.setOnClickListener {  }
                Snackbar.make(binding.llContainer,
                    "${item.name} 을(를) 장보기 메모에 추가했습니다.",
                    Snackbar.LENGTH_LONG).show()
            }


            llItem.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { setMargins(2, 2, 2, 2) }

            llItem.addView(tvItemName)
            llItem.addView(ivAddMemo)

            binding.llNotInFridgeContainer.addView(llItem)

        }

        val youtubePlayerView: YouTubePlayerView =
            view.findViewById<YouTubePlayerView>(R.id.detail_youtube_player_view)
        youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youtubePlayer: YouTubePlayer) { // Called when the player is ready to play video
                youtubePlayer.loadVideo(counterTop.recipe.videoId, 0F)
            }
        })

        binding.btnWatchLater.setOnClickListener {
            if (viewModel.isWatchLater) {
                libraryViewMemo.deleteLibrary(counterTop.recipe.videoId)
                binding.btnWatchLater.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_video_library_24_black))
                Snackbar.make(binding.llContainer,
                    "보관함에서 제거되었습니다.",
                    Snackbar.LENGTH_LONG).show()
                viewModel.isWatchLater = false
            }else {
                libraryViewMemo.addLibrary(Library(counterTop.recipe.videoId))
                binding.btnWatchLater.setImageDrawable(requireContext().getDrawable(R.drawable.ic_baseline_library_add_check_24))
                Snackbar.make(binding.llContainer,
                    "보관함에 저장되었습니다.",
                    Snackbar.LENGTH_LONG).show()
                viewModel.isWatchLater = true
            }
        }
    }

}