package monoalphabetic.cypher.di.repository.symbol

import android.content.Context
import monoalphabetic.cypher.application.App
import monoalphabetic.cypher.data.repository.symbol.SymbolRepository

class SymbolRepositoryProvider {

    fun provide(app: App) =
        SymbolRepository(
            ObservableCharMapStoreImpl(
                app.getSharedPreferences(
                    "SymbolRepository.CharMap",
                    Context.MODE_PRIVATE
                )
            )
        )

}