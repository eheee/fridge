package com.heee.fridgetube.data

import android.os.Parcelable
import com.heee.fridgetube.data.entity.Item
import com.heee.fridgetube.data.entity.Recipe
import kotlinx.parcelize.Parcelize

@Parcelize
class CounterTop(
    val recipe: Recipe,
    private val items: List<Item>,
    private val fridge: List<Long>
) : Parcelable {
    val inFridge = mutableSetOf<Item>()
    val notInFridge = mutableSetOf<Item>()

    init {
        for (item in items) {
            if (fridge.contains(item.id)) {
                inFridge.add(item)
            } else {
                notInFridge.add(item)
            }
        }
    }

    companion object : Comparator<CounterTop> {
        override fun compare(o1: CounterTop?, o2: CounterTop?): Int {
            return when {
                o1 == null -> 0
                o2 == null -> 0
                o1.notInFridge.size > o2.notInFridge.size -> 1
                else -> -1
            }
        }
    }
}
