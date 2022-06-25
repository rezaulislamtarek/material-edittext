package xyz.arifz.materialedittext

import android.content.res.Resources.*

object ExtensionFunctions {
    fun Int.dpToPx() = (this * getSystem().displayMetrics.density).toInt()
}