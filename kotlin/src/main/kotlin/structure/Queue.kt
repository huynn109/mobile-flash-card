package structure


fun main() {
    val queue = Queue()
    queue.add(0)
    queue.add(1)
    queue.add(2)
    println(queue.remove())
    println(queue.remove())
    println(queue.remove())
    println(queue.isEmpty())
}

/**
 * FIFO
 */
class Queue {
    class Node(val data: Int? = null, var next: Node? = null)

    private var head: Node? = null
    private var tail: Node? = null
    fun isEmpty(): Boolean {
        return head == null
    }

    fun peek(): Int? {
        return head?.data
    }

    fun add(data: Int) {
        val node = Node(data)
        if (tail != null)
            tail?.next = node
        tail = node
        if (head == null)
            head = node
    }

    fun remove(): Int? {
        val data = head?.data
        head = head?.next
        if (head == null)
            tail = null
        return data
    }
}