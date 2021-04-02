package com.heee.fridgetube.ui.fridge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.heee.fridgetube.R
import com.heee.fridgetube.data.CabinetAndItem
import com.heee.fridgetube.data.entity.Cabinet
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.databinding.FragmentFridgeBinding

class FridgeFragment : Fragment() {

    private val viewModel by viewModels<FridgeViewModel>()
    lateinit var binding: FragmentFridgeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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

        if (arguments != null) {
            val id = requireArguments().get("id") as Long?
            id?.let { viewModel.addCabinet(it) }
            viewModel.fetchCabinetAndItem()
        }

        binding.rvCabinets.layoutManager = LinearLayoutManager(context)
        val adapter = FridgeAdapter()
        binding.rvCabinets.adapter = adapter

        adapter.onItemClickListner = object : FridgeAdapter.OnItemClickListener {
            override fun onItemClick(cabinetAndItem: CabinetAndItem) {
                viewModel.deleteCabinet(cabinetAndItem.cabinet)
                Snackbar.make(binding.nestedScrollView,
                    "${cabinetAndItem.item.name} 식재료가 삭제되었습니다.",
                    Snackbar.LENGTH_LONG).show()
            }
        }

        viewModel.cabinet.observe(viewLifecycleOwner) {
            adapter.setList(it)
        }

    }
}