package com.heee.fridgetube.ui.fridge

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heee.fridgetube.R
import com.heee.fridgetube.databinding.FragmentFridgeBinding

class FridgeFragment : Fragment() {

    private val viewModel by viewModels<FridgeViewModel>()
    lateinit var binding: FragmentFridgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFridgeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddCabinet.setOnClickListener {
            findNavController().navigate(R.id.action_fridge_to_item)
        }

        if(arguments != null) {
            val id = requireArguments().get("id") as Long?
            id?.let { viewModel.addCabinet(it)}
            viewModel.fetchCabinetAndItem()
        }

        binding.rvCabinets.layoutManager = LinearLayoutManager(context)
        val adapter = FridgeAdapter()
        binding.rvCabinets.adapter = adapter

        viewModel.cabinet.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        })

    }
}