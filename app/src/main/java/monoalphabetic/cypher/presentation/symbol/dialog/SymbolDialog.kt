package monoalphabetic.cypher.presentation.symbol.dialog

import android.os.Bundle
import android.text.Editable
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
import util.extensions.onTextChanged
import util.extensions.setCharIfNotEquals
import util.text.SimpleTextWatcher

class SymbolDialog : BaseDialog() {

    val viewModel by viewModels<SymbolViewModel> { SymbolViewModelFactory(this, arguments) }

    val symbolWatcher = SimpleTextWatcher { viewModel.onSymbolTextChanged(it.firstOrNull()) }
    val replacementWatcher = SimpleTextWatcher { viewModel.onReplacementTextChanged(it.firstOrNull()) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.symbol_dialog, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDeleteButton()
        setListeners()
        setObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        symbolEdit.removeTextChangedListener(symbolWatcher)
        replacementEdit.removeTextChangedListener(replacementWatcher)
    }

    fun setDeleteButton() {
        deleteButton.isVisible = viewModel.showDeleteButton
    }

    fun setListeners() {
        saveButton.onClick(viewModel::onSaveClick)
        deleteButton.onClick(viewModel::onDeleteClick)
        symbolEdit.addTextChangedListener(symbolWatcher)
        replacementEdit.addTextChangedListener(replacementWatcher)
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
}