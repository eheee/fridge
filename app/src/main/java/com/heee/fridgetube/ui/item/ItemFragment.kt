package com.heee.fridgetube.ui.item

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.heee.fridgetube.R
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.databinding.FragmentItemBinding
import com.heee.fridgetube.ui.utils.DropDownView

class ItemFragment : Fragment() {

    lateinit var binding: FragmentItemBinding

    private val viewModel: ItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentItemBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val onItemClickListener = object : DropDownView.OnItemClickListener {
            override fun onClick(item: Item) {
                val bundle = bundleOf("id" to item.id)
                findNavController().navigate(R.id.navigation_fridge, bundle)
            }
        }

        val dropDownViewList: DropDownView = binding.dropDownMeat
        dropDownViewList.itemClickListener = onItemClickListener

        val dropDownViewList2: DropDownView = binding.dropDownSeasoning
        dropDownViewList2.itemClickListener = onItemClickListener

        viewModel.items.observe(viewLifecycleOwner) {
            for(item in it) {
                when(item.category) {
                    Item.Category.MEAT -> dropDownViewList.addItem(item)
                    Item.Category.LIQUID_SEASONING -> dropDownViewList2.addItem(item)
                }
            }
        }
    }
}