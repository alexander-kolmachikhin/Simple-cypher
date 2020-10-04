package monoalphabetic.cypher.presentation.symbol.dialog

import androidx.lifecycle.SavedStateHandle
import monoalphabetic.cypher.data.repository.symbol.SymbolRepository
import monoalphabetic.cypher.presentation.base.BaseViewModel
import util.livedata.NoValueSingleLiveData

class SymbolViewModel(
    private val state: SavedStateHandle,
    private val symbolRepository: SymbolRepository,
    symbol: Char?,
    replacement: Char?
) : BaseViewModel() {

    val showDeleteButton = !(symbol == null || replacement == null)
    val liveSymbol = state.getLiveData<Char?>(KEY_SYMBOL, symbol)
    val liveReplacement = state.getLiveData<Char?>(KEY_REPLACEMENT, replacement)
    val liveClose = NoValueSingleLiveData()

    fun onSymbolTextChanged(symbol: Char?) = setSymbol(symbol)

    fun onReplacementTextChanged(replacement: Char?) = setReplacement(replacement)

    fun onDeleteClick() {
        liveClose.notifyObservers()
        liveSymbol.value?.let(symbolRepository::remove)
    }

    fun onSaveClick() {
        liveClose.notifyObservers()
        liveSymbol.value?.let { char ->
            liveReplacement.value?.let { replacement ->
                symbolRepository.save(char, replacement)
            }
        }
    }

    private fun setSymbol(symbol: Char?) = state.set(KEY_SYMBOL, symbol)

    private fun setReplacement(replacement: Char?) = state.set(KEY_REPLACEMENT, replacement)

    companion object {
        const val KEY_SYMBOL = "KEY_SYMBOL"
        const val KEY_REPLACEMENT = "KEY_REPLACEMENT"
    }
}