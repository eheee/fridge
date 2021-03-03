package com.heee.fridgetube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.R

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)  // Let RecyclerView know the layout of RecyclerView has always same size.
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = VideoListAdapter(this.lifecycle)
        adapter.itemClickListener = object : VideoListAdapter.OnItemClickListener{
            override fun onItemClicked(position: Int, videoId: String) {
                val bundle = Bundle()
                bundle.putString("videoId", videoId)
                findNavController().navigate(R.id.action_home_to_detail, bundle)
            }
        }
        recyclerView.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner, Observer {
            adapter.setRecipes(it)
        })

        //Temp
        viewModel.getRecipes()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "뷰로딩", Toast.LENGTH_SHORT).show()
    }
}