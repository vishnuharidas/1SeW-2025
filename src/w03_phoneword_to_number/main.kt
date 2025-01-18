package w03_phoneword_to_number

fun main() {

    fun phoneWordToNumber(phoneWord: String): String {

        val t9Map = mapOf(
            '1' to '1',
            '2' to '2', 'A' to '2', 'B' to '2', 'C' to '2',
            '3' to '3', 'D' to '3', 'E' to '3', 'F' to '3',
            '4' to '4', 'G' to '4', 'H' to '4', 'I' to '4',
            '5' to '5', 'J' to '5', 'K' to '5', 'L' to '5',
            '6' to '6', 'M' to '6', 'N' to '6', 'O' to '6',
            '7' to '7', 'P' to '7', 'Q' to '7', 'R' to '7', 'S' to '7',
            '8' to '8', 'T' to '8', 'U' to '8', 'V' to '8',
            '9' to '9', 'W' to '9', 'X' to '9', 'Y' to '9', 'Z' to '9'
        )

        return phoneWord
            .map { t9Map[it] ?: it }
            .joinToString("")
    }


    listOf(
        "1-800-GOT-JUNK",
        "1-800-FLOWERS",
        "1-800-CONTACTS",
        "1-800-MATTRESS",
        "1-800-GO-UHAUL",
        "1-800-HOLIDAY",
        "1-800-GET-CASH",
        "1-800-NO-CABLE",
        "1-800-DRY-FAST",
        "1-800-YES-SNOW",
        "1-800-TAXICAB",
        "1-800-PAINTER",
        "1-800-REMODEL",
        "1-800-FIX-MEUP",
        "1-800-YOGA-NOW",
        "1-800-FIND-PET",
    ).forEach {
        println("$it >> ${phoneWordToNumber(it)}")
    }

}