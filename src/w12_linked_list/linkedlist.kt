package w12_linked_list

import kotlin.random.Random

internal class LinkedList<T> : Iterable<T> {

    data class Node<T>(
        val contents: T,
        var next: Node<T>? = null
    )

    private var start: Node<T>? = null
    private var end: Node<T>? = null

    val length: Int
        get() {
            if (start == null) return 0

            var len = 0
            var i = start
            do {
                len++
                i = i?.next
            } while (i != null)

            return len
        }

    fun insert(contents: T) {

        val node = Node(contents, null)

        if (start == null) {
            start = node
        } else {
            end?.next = node
        }

        end = node

    }

    fun remove(i: Int) {
        if (i >= length) throw IndexOutOfBoundsException("Maximum length is $length")
        if (i < 0) throw IndexOutOfBoundsException("Negative index $i")

        if (i == 0) {
            start = start?.next
            return
        }

        var node = start
        var pos = i
        while (pos != 1) {
            node = node?.next
            pos--
        }

        node?.next = node?.next?.next
    }

    fun itemAt(i: Int): T? {

        if (i >= length) throw IndexOutOfBoundsException("Length is $length")
        if (i < 0) throw IndexOutOfBoundsException("Negative index $i")

        var node = start
        var pos = i
        while (pos != 0) {
            node = node?.next
            pos--
        }

        return node?.contents

    }

    override fun iterator(): Iterator<T> {
        return object : Iterator<T> {

            var current = start

            override fun hasNext() = current != null

            override fun next(): T {
                if (!hasNext()) throw NoSuchElementException()
                val data = current!!.contents
                current = current?.next
                return data
            }

        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var i = start
        sb.append("LinkedList: ")

        if (length == 0) {
            sb.append("EMPTY")
            return sb.toString()
        }

        do {
            sb.apply {
                append("->[")
                append(i?.contents)
                append("]")
            }
            i = i?.next
        } while (i != null)

        return sb.toString()
    }
}

fun main() {

    val list = LinkedList<Int>()

    println(list)

    println("Insert:")
    repeat(20) {
        list.insert(it); println(list)
    }

    println("Item at 4 is : ${list.itemAt(4)}")

    println("Iterate:")
    val hundreds = list.map { it * 100 }
    println("Hundreds: $hundreds")

    val odds = list.filter { it % 2 != 0 }
    println("Odds: $odds")

    val shuffled = list.shuffled().map { it }
    println("Shuffled Numbers: $shuffled")

    println("Remove:")
    repeat(20) {
        val pos = Random.nextInt(0, list.length)
        list.remove(pos); println("Remove $pos: $list")
    }

}