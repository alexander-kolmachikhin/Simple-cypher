package simple.cypher.presentation.symbol.dialog

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import simple.cypher.application.App

class SymbolViewModelFactory(
    owner: SavedStateRegistryOwner,
    val arguments: Bundle?
) : AbstractSavedStateViewModelFactory(owner, arguments) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = SymbolViewModel(
        handle,
        App.instance.symbolRepository,
        arguments?.getChar("symbol"),
        arguments?.getChar("replacement")
    ) as T

}