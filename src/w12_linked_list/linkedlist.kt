package w12_linked_list

data class Node<T>(
    val contents: T,
    var next: Node<T>? = null
)

internal class LinkedList<T> {

    private var start: Node<T>? = null

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
            return
        }

        var i = start
        while (i?.next != null) {
            i = i.next
        }

        i?.next = node

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

    list.insert(1); println(list)
    list.insert(2); println(list)
    list.insert(3); println(list)
    list.insert(4); println(list)
    list.insert(5); println(list)

    println("Item at 4 is : ${list.itemAt(4)}")
}