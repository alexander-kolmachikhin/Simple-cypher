package simple.cypher.presentation.cypher

import androidx.lifecycle.SavedStateHandle
import simple.cypher.data.repository.symbol.SymbolRepository
import simple.cypher.presentation.base.BaseViewModel

class CypherViewModel(
    private val state: SavedStateHandle,
    private val symbolRepository: SymbolRepository
) : BaseViewModel() {

    val liveEditText = state.getLiveData<String>(KEY_EDIT_TEXT)
    val liveResultText = state.getLiveData<String>(KEY_RESULT_TEXT)

    private val liveSymbolMap = symbolRepository.getLiveSymbolMap()
    private val symbolMapObserver = { _: Map<Char, Char> ->
        setResultText(symbolRepository.encode(liveEditText.value ?: ""))
    }

    init {
        symbolRepository.getLiveSymbolMap().observeForever(symbolMapObserver)
    }

    fun onEditTextChanged(text: String) {
        setEditText(text)
        setResultText(symbolRepository.encode(text))
    }

    fun release() {
        liveSymbolMap.removeObserver(symbolMapObserver)
    }

    private fun setEditText(text: String) {
        if (liveEditText.value != text)
            state.set(KEY_EDIT_TEXT, text)
    }

    private fun setResultText(text: String) {
        if (liveResultText.value != text)
            state.set(KEY_RESULT_TEXT, text)
    }

    companion object {
        const val KEY_EDIT_TEXT = "KEY_EDIT_TEXT"
        const val KEY_RESULT_TEXT = "KEY_RESULT_TEXT"
    }
}