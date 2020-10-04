package simple.cypher.application

import android.app.Application
import simple.cypher.data.repository.symbol.SymbolRepository
import simple.cypher.di.repository.symbol.SymbolRepositoryProvider

class App : Application() {

    lateinit var symbolRepository: SymbolRepository

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        symbolRepository = SymbolRepositoryProvider().provide(this)
    }

    companion object {
        lateinit var instance: App
    }
}