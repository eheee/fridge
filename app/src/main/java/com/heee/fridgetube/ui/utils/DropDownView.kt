package com.heee.fridgetube.ui.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.heee.fridgetube.R
import com.heee.fridgetube.data.entity.Item

class DropDownView(
    context: Context,
    attrs: AttributeSet,
) : LinearLayout(context, attrs) {

    private var listContainer: LinearLayout
    private var arrow: ImageView
    private var categoryIcon: ImageView
    private var categoryTitle: TextView

    private var isExpanded: Boolean = false

    var itemClickListener: OnItemClickListener? = null

    init {
        inflate(context, R.layout.drop_down_view, this)

        listContainer = findViewById(R.id.list_container)
        arrow = findViewById(R.id.arrow)
        categoryTitle = findViewById(R.id.tv_category_title)
        categoryIcon = findViewById(R.id.iv_category_icon)
        val header = findViewById<ViewGroup>(R.id.header)

        header.setOnClickListener {
            if (isExpanded) {
                collapse()
            } else {
                expand()
            }
            isExpanded = !isExpanded
        }

        listContainer.visibility = View.GONE

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.DropDownView)
        categoryIcon.setImageDrawable(attributes.getDrawable(R.styleable.DropDownView_image))
        categoryTitle.text = attributes.getString(R.styleable.DropDownView_text)
        attributes.recycle()

    }

    fun addItem(item: Item) {
        val itemView: View = inflateItem(item)
        itemView.setOnClickListener {
            itemClickListener?.onClick(item)
        }
        listContainer.addView(itemView)
    }

    fun addAll(items: List<Item>) {
        for (item in items) {
            addItem(item)
        }
    }

    private fun expand() {
        arrow.setImageResource(R.drawable.ic_arrow_up)
        listContainer.visibility = View.VISIBLE
    }

    private fun collapse() {
        arrow.setImageResource(R.drawable.ic_arrow_down)
        listContainer.visibility = View.GONE
    }

    private fun inflateItem(item: Item): View {
        val dropDownListItem = View.inflate(context, R.layout.drop_down_item, null) as ViewGroup
        val tvItemName: TextView = dropDownListItem.findViewById(R.id.tv_drop_down_item_name)
        tvItemName.text = item.name
        return dropDownListItem
    }

    interface OnItemClickListener {
        fun onClick(item: Item)
    }

}
