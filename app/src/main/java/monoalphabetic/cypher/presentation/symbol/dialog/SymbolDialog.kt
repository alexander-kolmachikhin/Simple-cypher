package monoalphabetic.cypher.presentation.symbol.dialog

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.symbol_dialog.*
import monoalphabetic.cypher.R
import monoalphabetic.cypher.presentation.base.BaseDialog
import util.extensions.hideKeyboard
import util.extensions.onClick
import util.extensions.setCharIfNotEquals
import util.text.SimpleTextWatcher

class SymbolDialog : BaseDialog() {

    val viewModel by viewModels<SymbolViewModel> { SymbolViewModelFactory(this, arguments) }

    var symbolWatcher = SimpleTextWatcher { }
    var replacementWatcher = SimpleTextWatcher { }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.symbol_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setMode()
        setListeners()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        symbolEdit.removeTextChangedListener(symbolWatcher)
        replacementEdit.removeTextChangedListener(replacementWatcher)
        symbolWatcher = SimpleTextWatcher { }
        replacementWatcher = SimpleTextWatcher { }
    }

    fun setMode() {
        deleteButton.isVisible = viewModel.mode == SymbolViewModel.Mode.EDIT
        symbolEdit.isEnabled = viewModel.mode == SymbolViewModel.Mode.CREATE
    }

    fun setListeners() {
        symbolWatcher = SimpleTextWatcher { viewModel.onSymbolTextChanged(it.firstOrNull()) }
        replacementWatcher = SimpleTextWatcher { viewModel.onReplacementTextChanged(it.firstOrNull()) }
        symbolEdit.addTextChangedListener(symbolWatcher)
        replacementEdit.addTextChangedListener(replacementWatcher)
        deleteButton.onClick(viewModel::onDeleteClick)
        saveButton.onClick(viewModel::onSaveClick)
    }

    fun setObservers() {
        viewModel.liveClose(this::close)
        viewModel.liveSymbol(symbolEdit::setCharIfNotEquals)
        viewModel.liveReplacement(replacementEdit::setCharIfNotEquals)
    }

    fun close() {
        hideKeyboard()
        dismiss()
    }

    override fun dismiss() {
        /**
         * Leak fix
         */
        symbolEdit.isCursorVisible = false
        replacementEdit.isCursorVisible = false
        super.dismiss()
    }
}