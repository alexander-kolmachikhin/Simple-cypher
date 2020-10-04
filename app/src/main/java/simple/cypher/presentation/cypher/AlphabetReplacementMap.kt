package simple.cypher.presentation.cypher

class AlphabetReplacementMap(private val map: Map<Char, Char>) {

    fun getReplacementByChar(char: Char) = map[char]

    fun getCharByReplacement(replacement: Char) = map.entries.find { it.value == replacement }?.key

}