package com.heee.fridgetube.ui.memo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heee.fridgetube.R

class MemoFragment : Fragment() {

    companion object {
        fun newInstance() = MemoFragment()
    }

    private lateinit var viewModel: MemoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_memo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}