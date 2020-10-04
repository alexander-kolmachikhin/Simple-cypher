package monoalphabetic.cypher.presentation.cypher

import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.mctech.library.keyboard.visibilitymonitor.KeyboardVisibilityMonitor
import kotlinx.android.synthetic.main.cypher_fragment.*
import monoalphabetic.cypher.R
import monoalphabetic.cypher.presentation.base.BaseFragment
import util.extensions.setTextIfNotEquals
import util.text.SimpleTextWatcher

class CypherFragment : BaseFragment(R.layout.cypher_fragment) {

    val viewModel by viewModels<CypherViewModel> { CypherViewModelFactory(this) }

    var editWatcher = SimpleTextWatcher { }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        editText.removeTextChangedListener(editWatcher)
        editWatcher = SimpleTextWatcher { }
        viewModel.release()
    }

    fun setListeners() {
        editWatcher = SimpleTextWatcher(viewModel::onEditTextChanged)
        editText.addTextChangedListener(editWatcher)
        KeyboardVisibilityMonitor(viewLifecycleOwner, activity as AppCompatActivity) {
            if (!it.isOpened) {
                editText.clearFocus()
            }
        }
    }

    fun setObservers() {
        viewModel.liveEditText(editText::setTextIfNotEquals)
        viewModel.liveResultText(resultText::setTextIfNotEquals)
    }
}