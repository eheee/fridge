package com.heee.fridgetube.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.R
import com.heee.fridgetube.data.CounterTop
import com.heee.fridgetube.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvCounterTop.setHasFixedSize(true)
        binding.rvCounterTop.layoutManager = LinearLayoutManager(requireContext())

        val adapter = VideoListAdapter(object : VideoListAdapter.OnItemClickListener{
            override fun onItemClicked(counterTop: CounterTop) {
                val bundle = Bundle()
                bundle.putParcelable("counterTop", counterTop)
                findNavController().navigate(R.id.action_goto_detail, bundle)
            }
        })
        binding.rvCounterTop.adapter = adapter

        viewModel.recipes.observe(viewLifecycleOwner) {
            if(it.size > 1){
                binding.llHomeInstruction.visibility = View.GONE
            }else {
                binding.llHomeInstruction.visibility = View.VISIBLE
            }
            adapter.setRecipes(it.toList())
        }

        viewModel.getRecipes()

        return binding.root
    }
}