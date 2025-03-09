package ru.glindaquint.everwell.utils

import android.content.res.Resources
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

fun Int.pxToDp(): Dp {
    val density = Resources.getSystem().displayMetrics.density
    return (this / density).roundToInt().dp
}

fun Int.spToDp(): Dp {
    val density = Resources.getSystem().displayMetrics.density
    return (this * (density / 160)).roundToInt().dp
}
