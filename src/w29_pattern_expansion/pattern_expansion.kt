package w29_pattern_expansion

internal fun expandPattern(pattern: String): String {
    val stack = mutableListOf<String>()

    fun <E> MutableList<E>.push(value: E) {
        this.add(value)
    }

    fun <E> MutableList<E>.pop(): E {
        if (this.isEmpty()) throw NoSuchElementException("Stack is empty")
        return this.removeAt(this.size - 1)
    }

    fun String.isDigit(): Boolean {
        return this.length == 1 && this[0].isDigit()
    }

    fun <E> MutableList<E>.popUntil(predicate: (E) -> Boolean): List<E> {
        val poppedItems = mutableListOf<E>()
        while (this.isNotEmpty() && !predicate(this.last())) {
            poppedItems.add(this.removeAt(this.size - 1))
        }
        return poppedItems.reversed() // Return in the order they were popped
    }

    for (char in pattern) {

        when {
            char.isDigit() -> {
                stack.push(char.toString())
            }

            char.isLetter() -> {
                if (stack.isNotEmpty() && stack.last().isDigit()) {
                    val count = stack.pop()
                    stack.push(char.toString().repeat(count.toInt()))
                } else {
                    stack.push(char.toString())
                }
            }

            char == '[' -> {
                stack.push("[")
            }

            char == ']' -> {
                val parts = stack.popUntil { it == "[" }.joinToString("")
                stack.pop() // Remove the "[" from the stack

                val repeatCount = stack.pop().toIntOrNull() ?: 1
                stack.push(parts.repeat(repeatCount))
            }

        }
    }

    return stack.popUntil { it.isEmpty() }.joinToString("")

}

fun main() {

    val pattern = "2a3x4[bcd5[X]]ef" // Example pattern

    val expandedParts = expandPattern(pattern)

    println("Pattern: $pattern")
    println("Expanded: $expandedParts")
}