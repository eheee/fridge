package com.heee.fridgetube.ui.fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.setPadding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.heee.fridgetube.R
import com.heee.fridgetube.databinding.FragmentFridgeBinding
import com.heee.fridgetube.ui.utils.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.floatingAddCabinet.setOnClickListener {
            findNavController().navigate(R.id.action_goto_item)
        }

        if(arguments != null) {
            val id = requireArguments().get("id") as Long?
            id?.let { viewModel.addCabinet(it)}
            viewModel.fetchCabinetAndItem()
        }

        binding.rvCabinets.layoutManager = LinearLayoutManager(context)
        val adapter = FridgeAdapter()
        binding.rvCabinets.adapter = adapter

        viewModel.cabinet.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }

    }
}