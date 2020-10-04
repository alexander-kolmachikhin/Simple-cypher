package simple.cypher.di.repository.symbol

import android.content.SharedPreferences
import androidx.core.content.edit
import simple.cypher.data.repository.symbol.ObservableCharMapStore

class ObservableCharMapStoreImpl(private val preferences: SharedPreferences) : ObservableCharMapStore {

    private val charMap = HashMap<Char, Char>()
    private var observer = { _: Map<Char, Char> -> Unit }

    init {
        preferences.all.forEach { entry ->
            entry.run {
                kotlin.runCatching {
                    charMap[key.toString().first()] = value.toString().first()
                }
            }
        }
    }

    override fun save(key: Char, value: Char) {
        preferences.edit { putString(key.toString(), value.toString()) }
        charMap[key] = value
        observer(charMap)
    }

    override fun remove(key: Char) {
        preferences.edit { putString(key.toString(), null) }
        charMap.remove(key)
        observer(charMap)
    }

    override fun observe(callback: (Map<Char, Char>) -> Unit) {
        observer = callback
        observer(charMap)
    }

}