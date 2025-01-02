package w01_harshad_number

fun main() {

    val sumOfDigits = { num: Int -> "$num".map { it.digitToInt() }.sum() }

    val isHarshadNumber = { num: Int -> num % sumOfDigits(num) == 0 }

    println(isHarshadNumber(2025)) // true
    println(isHarshadNumber(2026)) // false

    // Find all years in 21st century that are Harshad numbers
    val harshadYears = (2001..2100).filter(isHarshadNumber)
    println(harshadYears)
}