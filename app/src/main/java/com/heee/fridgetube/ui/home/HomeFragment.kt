package com.heee.fridgetube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val videoIds = arrayOf(
            "6JYIGclVQdw",
            "LvetJ9U_tVY",
            "6JYIGclVQdw",
            "LvetJ9U_tVY",
            "6JYIGclVQdw",
            "LvetJ9U_tVY",
            "6JYIGclVQdw",
            "LvetJ9U_tVY",
            "6JYIGclVQdw",
            "LvetJ9U_tVY",
            "6JYIGclVQdw",
            "LvetJ9U_tVY"
        )

        val recyclerView: RecyclerView = root.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)  // Let RecyclerView know the layout of RecyclerView has always same size.
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = VideoListAdapter(videoIds, this.lifecycle)
        adapter.itemClickListener = object : VideoListAdapter.OnItemClickListener{
            override fun onItemClicked(position: Int, videoId: String) {
                Toast.makeText(requireContext(), "$videoId + 클릭", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView.adapter = adapter

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(requireContext(), "뷰로딩", Toast.LENGTH_SHORT).show()
    }
}