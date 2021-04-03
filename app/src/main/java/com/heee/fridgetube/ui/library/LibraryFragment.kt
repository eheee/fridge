package com.heee.fridgetube.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heee.fridgetube.R
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.databinding.FragmentLibraryBinding
import com.heee.fridgetube.ui.home.VideoListAdapter

class LibraryFragment : Fragment() {

    private val viewModel: LibraryViewModel by viewModels()
    private lateinit var binding: FragmentLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvLibrary.layoutManager = LinearLayoutManager(context)
        val adapter = VideoListAdapter(object : VideoListAdapter.OnItemClickListener {

            override fun onItemClicked(counterTop: CounterTop) {
                val bundle = Bundle()
                bundle.putParcelable("counterTop", counterTop)
                findNavController().navigate(R.id.action_goto_detail, bundle)
            }
        })
        binding.rvLibrary.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner){
            adapter.setRecipes(it)
        }

        viewModel.fetchLibrary()
    }

}