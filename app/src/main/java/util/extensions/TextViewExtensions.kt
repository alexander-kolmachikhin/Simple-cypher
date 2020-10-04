package util.extensions

import android.text.Selection.setSelection
import android.widget.TextView


fun TextView.setTextIfNotEquals(t: String) {
    if (text?.toString() != t) {
        text = t
    }
}

fun TextView.setCharIfNotEquals(t: Char?) {
    if (t == null) return
    if (text?.toString() != t.toString()) {
        text = t.toString()
    }
}