package com.heee.fridgetube.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heee.fridgetube.R

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

}