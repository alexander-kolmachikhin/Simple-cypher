package simple.cypher.data.repository.symbol

interface ObservableCharMapStore {

    fun save(key: Char, value: Char)

    fun remove(key: Char)

    fun observe(callback: (Map<Char, Char>) -> Unit)

}