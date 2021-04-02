package com.heee.fridgetube.ui.item

import android.os.Bundle
import android.util.Log
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

        //FIXME make it easy to maintain.
        val dropDownMeat: DropDownView = binding.dropDownMeat
        dropDownMeat.itemClickListener = onItemClickListener

        val dropDownHam: DropDownView = binding.dropDownHam
        dropDownHam.itemClickListener = onItemClickListener

        val dropDownLeaf: DropDownView = binding.dropDownLeaf
        dropDownLeaf.itemClickListener = onItemClickListener

        val dropDownMushroom: DropDownView = binding.dropDownMushroom
        dropDownMushroom.itemClickListener = onItemClickListener

        val dropDownPumpkin: DropDownView = binding.dropDownPumpkin
        dropDownPumpkin.itemClickListener = onItemClickListener

        val dropDownFish: DropDownView = binding.dropDownFish
        dropDownFish.itemClickListener = onItemClickListener

        val dropDownBean: DropDownView = binding.dropDownBean
        dropDownBean.itemClickListener = onItemClickListener

        val dropDownMilk: DropDownView = binding.dropDownMilk
        dropDownMilk.itemClickListener = onItemClickListener

        val dropDownSeasoning: DropDownView = binding.dropDownSeasoning
        dropDownSeasoning.itemClickListener = onItemClickListener

        val dropDownJar: DropDownView = binding.dropDownJar
        dropDownJar.itemClickListener = onItemClickListener

        val dropDownOil: DropDownView = binding.dropDownOil
        dropDownOil.itemClickListener = onItemClickListener

        val dropDownNuddle: DropDownView = binding.dropDownNuddle
        dropDownNuddle.itemClickListener = onItemClickListener

        viewModel.items.observe(viewLifecycleOwner) {
            for(item in it) {
                when(item.category) {
                    Item.Category.MEAT -> dropDownMeat.addItem(item)
                    Item.Category.HAM_CAN_EGG -> dropDownHam.addItem(item)
                    Item.Category.LIQUID_SEASONING -> dropDownSeasoning.addItem(item)
                    Item.Category.LEAF_ONION -> dropDownLeaf.addItem(item)
                    Item.Category.MUSHROOM_ROOT -> dropDownMushroom.addItem(item)
                    Item.Category.PEPPER_PUMPKIN -> dropDownPumpkin.addItem(item)
                    Item.Category.FISH_SEAFOOD -> dropDownFish.addItem(item)
                    Item.Category.NUT_BEAN -> dropDownBean.addItem(item)
                    Item.Category.KIMCHI_MILK -> dropDownMilk.addItem(item)
                    Item.Category.LIQUID_SEASONING -> dropDownSeasoning.addItem(item)
                    Item.Category.SOLID_SEASONING -> dropDownJar.addItem(item)
                    Item.Category.SOURCE_OIL -> dropDownOil.addItem(item)
                    Item.Category.NUDDLE_RICE -> dropDownNuddle.addItem(item)
                    else ->
                        Log.e(TAG, "Not proper category type (category:${item.category})")
                }
            }
        }
    }

    companion object {
        const val TAG = "ItemFragment"
    }
}