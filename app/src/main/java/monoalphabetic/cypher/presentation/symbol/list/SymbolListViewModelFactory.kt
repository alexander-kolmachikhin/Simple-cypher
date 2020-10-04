package monoalphabetic.cypher.presentation.symbol.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import monoalphabetic.cypher.application.App

class SymbolListViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        SymbolListViewModel(App.instance.symbolRepository) as T

}