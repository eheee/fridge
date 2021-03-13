package com.heee.fridgetube.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.heee.fridgetube.R
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.databinding.FragmentItemBinding

class ItemFragment : Fragment() {

    lateinit var binding: FragmentItemBinding

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ItemsAdapter()
        adapter.onItemClickListener = object: ItemsAdapter.OnItemClickListener{
            override fun setItemClickListenr(item: Item) {
                val bundle = bundleOf("id" to item.id)
                findNavController().navigate(R.id.navigation_fridge, bundle)
            }
        }

        binding.rvItems.layoutManager = LinearLayoutManager(context)
        binding.rvItems.adapter = adapter

        viewModel.items.observe(viewLifecycleOwner, Observer {
            adapter.setItemList(it)
        })
    }
}