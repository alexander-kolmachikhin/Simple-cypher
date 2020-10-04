package simple.cypher.presentation.symbol.list

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.Transformations
import simple.cypher.data.repository.symbol.SymbolRepository
import simple.cypher.presentation.base.BaseViewModel
import simple.cypher.presentation.symbol.list.adapter.SymbolItem
import util.livedata.SingleLiveData

class SymbolListViewModel(symbolRepository: SymbolRepository) : BaseViewModel() {

    val liveOpenSymbolDialog = SingleLiveData<Bundle?>()
    val liveSymbolItemList = Transformations.map(symbolRepository.getLiveSymbolMap()) { map ->
        map.map {
            SymbolItem(it.key, it.value)
        }.sortedBy { it.symbol }
    }

    fun onItemClick(item: SymbolItem) = liveOpenSymbolDialog.setValue(
        bundleOf(
            "symbol" to item.symbol,
            "replacement" to item.replacement
        )
    )

    fun onAddButtonClick() = liveOpenSymbolDialog.setValue(null)

}