package com.heee.fridgetube.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RecipeCard(
    val recipe: Recipe,
    val itemsInFridge: List<Long>
) : Parcelable {
    val inFridge = mutableSetOf<Item>()
    val notInFridge = mutableSetOf<Item>()

    init {

    }

    companion object : Comparator<RecipeCard> {
        override fun compare(o1: RecipeCard?, o2: RecipeCard?): Int {
            return when {
                o1 == null -> 0
                o2 == null -> 0
                o1.notInFridge.size > o2.notInFridge.size -> 1
                else -> -1
            }
        }
    }
}
