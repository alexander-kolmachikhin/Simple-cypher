package monoalphabetic.cypher.presentation.cypher

class MonoalphabeticCypher(private val map: AlphabetReplacementMap) {


    fun encode(text: String) = text.replaceCharsBy { map.getReplacementByChar(it) ?: it }

    fun decode(text: String) = text.replaceCharsBy { map.getCharByReplacement(it) ?: it }

    private fun String.replaceCharsBy(callback: (Char) -> Char) =
        StringBuilder().let { builder ->
            forEach { builder.append(callback(it)) }
            builder.toString()
        }
}