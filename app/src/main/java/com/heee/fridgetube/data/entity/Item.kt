package com.heee.fridgetube.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val category: Category = Category.UNDEFINED,
    val isSpecial: Boolean = false
) : Parcelable {

    enum class Category {
        UNDEFINED,
        MEAT,
        HAM_CAN_EGG,
        VEGETABLE,
        FISH_SEAFOOD,
        FRUIT_NUT_BEAN,
        KIMCHI_MILK,
        SOURCE_SEASONING_OIL,
        NUDDLE_RICE
    }

    class Converter {

        @TypeConverter
        fun toCategory(value: Int) = enumValues<Category>()[value]

        @TypeConverter
        fun fromCategory(category: Category) = category.ordinal
    }
}