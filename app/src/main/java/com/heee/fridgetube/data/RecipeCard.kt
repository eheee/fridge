package com.heee.fridgetube.data

class RecipeCard(
    val recipe: Recipe,
) {
    val inFridge = mutableSetOf<Item>()
    val notInFridge = mutableSetOf<Item>()

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
