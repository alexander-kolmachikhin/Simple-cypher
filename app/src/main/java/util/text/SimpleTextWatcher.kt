package util.text

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(private val onTextChangedCallback: (String) -> Unit) : TextWatcher {

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = onTextChangedCallback(s?.toString() ?: "")

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun afterTextChanged(s: Editable?) {}

}