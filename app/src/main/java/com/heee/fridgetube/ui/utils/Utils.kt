package com.heee.fridgetube.ui.utils

import android.view.View
import android.widget.TextView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.heee.fridgetube.R

fun TextView.toRoundedShape() {
    val radius = resources.getDimension(R.dimen.default_corner_radius)
    val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, radius)
            .build()
    val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
    background = shapeDrawable
}