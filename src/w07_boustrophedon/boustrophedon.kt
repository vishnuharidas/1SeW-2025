package w07_boustrophedon

fun main() {

    val text =
        "Boustrophedon is a style of writing in which alternate lines of writing are reversed, with letters also written in reverse, mirror-style. This is in contrast to modern European languages, where lines always begin on the same side, usually the left."
    val columns = 40

    // Easy way - just split the characters by col count and then reverse the alternate ones.
    val result1 = text
        .chunked(columns)
        .mapIndexed { index, s -> if (index % 2 != 0) s.reversed() else s }

    println()
    println("$columns Columns - Easy Way (with split word):")
    repeat(columns) { print("|") }
    println()
    result1.forEach { println(it) }

    // Hard way - keep the words without splitting.
    val lines = mutableListOf<StringBuilder>()

    text.split(" ").forEach { word ->

        if (lines.isEmpty() || lines.last().length + word.length > columns) {
            lines.add(StringBuilder())
        }

        lines.last().append(word).append(" ")

    }

    val result2 = lines
        .mapIndexed { index, s -> if (index % 2 != 0) s.reversed() else s }

    println()
    println("$columns Columns - Hard Way (no words split):")
    repeat(columns) { print("|") }
    println()
    result2.forEach { println(it) }
}