package w17_jenkins_hash

fun main(args: Array<String>) {

    // Jenkin's Hash
    fun jenkinsHash(input: String): String {
        var hash = 0
        for (b in input.toByteArray()) {
            hash += b.toInt() and 0xFF
            hash += hash shl 10
            hash = hash xor (hash ushr 6)
        }
        hash += hash shl 3
        hash = hash xor (hash ushr 11)
        hash += hash shl 15
        return hash.toUInt().toString(16).padStart(8, '0')
    }

    // If command-line arguments are provided, hash each and exit
    if (args.isNotEmpty()) {
        args.forEach { println("$it -> ${jenkinsHash(it)}") }
        return
    }

    // Otherwise, hash the default character set
    val chars = buildList<Char> {
        addAll('0'..'9')
        addAll('a'..'z')
        addAll('A'..'Z')
        val symbols = listOf(
            '`', '~', '!', '@', '#', '$', '%', '^', '&', '*',
            '(', ')', '-', '_', '=', '+', '[', ']', '{', '}',
            '\\', '|', ';', ':', '\'', '"', ',', '<', '.', '>',
            '/', '?'
        )
        addAll(symbols)
    }

    for (c in chars) {
        println("$c -> ${jenkinsHash(c.toString())}")
    }

    println(jenkinsHash("The quick brown fox jumps over the lazy dog!"))
}
