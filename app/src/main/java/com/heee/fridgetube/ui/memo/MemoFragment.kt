package com.heee.fridgetube.ui.memo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.heee.fridgetube.R
import com.heee.fridgetube.data.Memo
import com.heee.fridgetube.databinding.FragmentMemoBinding

class MemoFragment : Fragment() {

    private val viewModel: MemoViewModel by viewModels()

    private lateinit var binding: FragmentMemoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMemo.layoutManager = LinearLayoutManager(context)
        val adapter = MemoAdapter()
        adapter.onItemClinkListener = object : MemoAdapter.OnItemClinkListener {
            override fun itemClick(memo: Memo) {
                viewModel.deleteMemo(memo)
            }
        }
        binding.rvMemo.adapter = adapter

        viewModel.memo.observe(viewLifecycleOwner) {
            adapter.setMemoList(it)
        }

        viewModel.getMemos()
    }
}