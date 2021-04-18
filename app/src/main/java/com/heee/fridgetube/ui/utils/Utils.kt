package com.heee.fridgetube.ui.utils

import android.view.View
import android.widget.TextView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.snackbar.Snackbar
import com.heee.fridgetube.R

fun TextView.toRoundedShape() {
    val radius = resources.getDimension(R.dimen.default_corner_radius)
    val shapeAppearanceModel = ShapeAppearanceModel().toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, radius)
            .build()
    val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel)
    background = shapeDrawable
}

fun View.visible(isVisiable: Boolean) {
    visibility = if (isVisiable) View.VISIBLE else View.GONE
}

fun View.enable(enable: Boolean) {
    isEnabled = enable
    alpha = if (enable) 1f else 0.5f // 1f 완전 불투명, 0.5 반투명
}

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}
