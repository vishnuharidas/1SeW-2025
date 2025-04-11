package w15_dict_zip


fun main() {

    fun zip(string: String): String {

        val words = string.split(" ")

        val zipped = words.map {

            if (it in dictionary) {
                "${Char(dictionary.indexOf(it))}"
            } else {
                "/$it"
            }

        }

        return zipped.joinToString(" ")

    }

    fun unzip(string: String): String {

        val words = string.split(" ")

        val unzipped = words.map {

            if (it.startsWith("/")) {
                it.substring(1, it.length)
            } else {
                dictionary[it[0].code]
            }

        }

        return unzipped.joinToString(" ")

    }

    listOf(story01, story02, story03, story04, story05).forEach { string ->

        val zipped = zip(string)

        val unzipped = unzip(zipped)

        require(string == unzipped) {
            "Unzipped string does not match original string"
        }

        val compressionRatio = (1 - (zipped.length.toDouble() / string.length.toDouble())) * 100
        println("Original size: ${string.length} characters")
        println("Compressed size: ${zipped.length} characters")
        println("Compression ratio: ${compressionRatio.toInt()}%")

        println("----------------------")

    }

}