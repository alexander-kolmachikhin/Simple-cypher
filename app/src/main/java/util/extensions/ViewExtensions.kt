package util.extensions

import android.view.View

fun View.onClick(listener: () -> Unit) {
    setOnClickListener {
        listener()
    }
}