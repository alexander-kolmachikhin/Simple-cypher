package simple.cypher.presentation.cypher

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import simple.cypher.application.App

class CypherViewModelFactory(owner: SavedStateRegistryOwner) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ) = CypherViewModel(
        handle,
        App.instance.symbolRepository
    ) as T

}