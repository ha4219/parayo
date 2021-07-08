package io.github.ha4219.ttd.view

import android.graphics.Color
import android.graphics.drawable.*


private fun borderBG(
    borderColor: String = "#1F000000",
    bgColor: String = "#FFFFFF",
    borderWidthLeft: Int = 0,
    borderWidthTop: Int = 0,
    borderWidthRight: Int = 0,
    borderWidthBottom: Int = 0
): LayerDrawable {
    val layerDrawable = arrayOf<Drawable>(
        ColorDrawable(Color.parseColor(borderColor)),
        ColorDrawable(Color.parseColor(bgColor))
    ).let(::LayerDrawable)

    layerDrawable.setLayerInset(
        1,
        borderWidthLeft,
        borderWidthTop,
        borderWidthRight,
        borderWidthBottom
    )

    return layerDrawable
}

fun borderLeft(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderWidthLeft = width
)

fun borderRight(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderWidthRight = width
)

fun borderTop(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderWidthTop = width
)

fun borderBottom(
    color: String = "#1F000000",
    width: Int
) = borderBG(
    borderColor = color,
    borderWidthBottom = width
)