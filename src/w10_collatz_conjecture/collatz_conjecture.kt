package w10_collatz_conjecture

import kotlin.random.Random
import kotlin.random.nextULong

fun main() {

    // This will return only if the number reduces to 1.
    fun conjectureTest(n: ULong) {

        var number = n

        while (number != 1UL) {
            if (number % 2UL == 0UL) {
                number /= 2U
            } else {
                number = number * 3U + 1U
            }
        }
    }

    // Return each step for a given number to reach 1.
    // This function assumes that the conjecture is true for the given number.
    fun conjecture(n: ULong): List<ULong> {

        val list = mutableListOf<ULong>().also { it.add(n) }

        var number = n

        while (number != 1UL) {

            if (number % 2UL == 0UL) {
                number /= 2U
            } else {
                number = number * 3U + 1U
            }

            list.add(number)
        }

        return list
    }

    // Test 10 random numbers
    (1..10)
        .map { Random.nextULong(100_000_000_000U) } // 100 Billion
        .forEach {
            println()
            print("Checking $it...")
            conjectureTest(it)
            print("Done.")
        }

    // Pick 10 random numbers and try.
    (1..10)
        .map { Random.nextULong(100_000_000UL) }
        .associateWith { conjecture(it) }
        .map {
            println("Number: ${it.key}, Steps count: ${it.value.size}, Steps: ${it.value}")
        }
}