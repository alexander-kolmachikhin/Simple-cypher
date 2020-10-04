package simple.cypher.data.repository.symbol

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import simple.cypher.presentation.cypher.AlphabetReplacementMap
import simple.cypher.presentation.cypher.MonoalphabeticCypher

class SymbolRepository(private val store: ObservableCharMapStore) {

    private val liveSymbolMap = MutableLiveData<Map<Char, Char>>()

    fun getLiveSymbolMap() = liveSymbolMap as LiveData<Map<Char, Char>>

    init {
        store.observe(liveSymbolMap::setValue)
    }

    fun save(symbol: Char, replacement: Char) = store.save(symbol, replacement)

    fun remove(symbol: Char) = store.remove(symbol)

    fun decode(text: String) = liveSymbolMap.value?.let {
        MonoalphabeticCypher(AlphabetReplacementMap(it)).decode(text)
    } ?: ""

    fun encode(text: String) = liveSymbolMap.value?.let {
        MonoalphabeticCypher(AlphabetReplacementMap(it)).encode(text)
    } ?: ""

}