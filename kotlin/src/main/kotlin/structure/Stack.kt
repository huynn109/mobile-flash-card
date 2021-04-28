package structure

import java.util.*
import java.util.Queue

fun main() {
    val stack = Stack()
    stack.push(0)
    stack.push(1)
    stack.push(2)
    println(stack.pop())
    println(stack.peek())
    println(stack.pop())
    println(stack.pop())
}

/**
 * LIFO
 */
class Stack {
    class Node(val data: Int? = null, var next: Node? = null)

    private var top: Node? = null
    fun isEmpty(): Boolean {
        return top == null
    }

    fun peek(): Int? {
        return top?.data
    }

    fun push(data: Int) {
        val node = Node(data)
        node.next = top
        top = node
    }

    fun pop(): Int? {
        val data = top?.data
        top = top?.next
        return data
    }
}