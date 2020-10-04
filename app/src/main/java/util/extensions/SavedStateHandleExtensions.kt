package util.extensions

import androidx.lifecycle.SavedStateHandle

fun SavedStateHandle.ifNotContains(vararg keys: String, function: () -> Unit) {
    for (key in keys) {
        if (!contains(key)) {
            function()
            return
        }
    }
}