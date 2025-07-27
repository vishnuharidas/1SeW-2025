package w30_stalin_sort

fun main() {

    val randomIntegers = List(100) { (0..100).random() }
    println("Original List: $randomIntegers")

    val sorted = mutableListOf(randomIntegers.first())

    // Sort using Stalin sort algorithm
    for (i in 1 until randomIntegers.size) {
        if (randomIntegers[i] > sorted.last()) {
            sorted.add(randomIntegers[i])
        }
    }

    println("Sorted List: $sorted")

}