package util.extensions

import android.widget.EditText
import androidx.core.widget.addTextChangedListener

fun EditText.onTextChanged(listener: (String) -> Unit) {
    addTextChangedListener {
        listener(it?.toString() ?: "")
    }
}