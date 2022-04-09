package coroutine.basic.Basic02

import kotlinx.coroutines.*

fun main() = runBlocking { // this: CoroutineScope
    launch {
        println("Hello1")
        doWorld()
    }
    println("Hello")
}

// this is your first suspending function
suspend fun doWorld() {
    delay(1000L)
    println("World!")
}