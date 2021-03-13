package com.heee.fridgetube.ui.library

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heee.fridgetube.R
import com.heee.fridgetube.data.RecipeCard
import com.heee.fridgetube.databinding.FragmentLibraryBinding
import com.heee.fridgetube.ui.home.VideoListAdapter

class LibraryFragment : Fragment() {

    private lateinit var viewModel: LibraryViewModel
    private lateinit var binding: FragmentLibraryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)

        binding.rvLibrary.layoutManager = LinearLayoutManager(context)
        val adapter = VideoListAdapter(object : VideoListAdapter.OnItemClickListener {

            override fun onItemClicked(recipeCard: RecipeCard) {
                val bundle = Bundle()
                bundle.putParcelable("recipeCard", recipeCard)
                findNavController().navigate(R.id.action_goto_detail, bundle)
            }
        })
        binding.rvLibrary.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner){
            adapter.setRecipes(it)
        }

        viewModel.getLibrary()

    }

}