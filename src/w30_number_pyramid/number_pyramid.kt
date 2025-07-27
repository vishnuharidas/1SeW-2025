package w30_number_pyramid

/*
Found this pattern in a reel, which found interesting, so thought of trying this week.

Input N = 6
pattern:
1
7   2
12  8   3
16  13  9   4
19  17  14  10  5
22  20  18  15  11  6
*/

fun main() {
    val n = 20

    for (i in 0..n - 1) {

        val row = mutableListOf(i + 1)

        for (j in 1..i) {
            row.add(row.last() + n - j)  // Kotlin's `List.last()` complexity is O(1)
        }

        println(row.reversed().joinToString("\t"))
    }
}