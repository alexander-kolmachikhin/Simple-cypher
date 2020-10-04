package monoalphabetic.cypher.di.repository.symbol

import android.content.Context
import androidx.core.content.edit
import monoalphabetic.cypher.application.App
import monoalphabetic.cypher.data.repository.symbol.SymbolRepository

class SymbolRepositoryProvider {

    fun provide(app: App) = SymbolRepository(
        ObservableCharMapStoreImpl(
            app.getSharedPreferences(
                "SymbolRepository.CharMap",
                Context.MODE_PRIVATE
            )
        )
    ).apply {
        val sharedPreferences = app.getSharedPreferences("SymbolRepository", Context.MODE_PRIVATE)
        if (!sharedPreferences.getBoolean("StartValuesInserted", false)) {
            mapOf(
                'a' to 'q',
                'b' to 'w',
                'c' to 'e',
                'd' to 'r',
                'e' to 't',
                'f' to 'y',
                'g' to 'u',
                'h' to 'i',
                'i' to 'o',
                'j' to 'p',
                'k' to 'a',
                'l' to 's',
                'm' to 'd',
                'n' to 'f',
                'o' to 'g',
                'p' to 'h',
                'q' to 'j',
                'r' to 'k',
                's' to 'l',
                't' to 'z',
                'u' to 'x',
                'v' to 'c',
                'w' to 'v',
                'x' to 'b',
                'y' to 'n',
                'z' to 'm'
            ).forEach { save(it.key, it.value) }

            sharedPreferences.edit { putBoolean("StartValuesInserted", true) }
        }
    }

}