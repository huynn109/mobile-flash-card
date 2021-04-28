package thread

import kotlinx.coroutines.*
import java.lang.Runnable
import java.lang.Thread.currentThread
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis


fun main() {
    ThreadExample().apply { start() }
//    val thread1: Thread
//    RunnableExample().apply { thread1 = Thread(this) }
//    thread1.start()
//    testRunThread()
    testRunCoroutine()
}

class ThreadExample : Thread() {
    override fun run() {
        println(currentThread().name)
    }
}

class RunnableExample : Runnable {
    override fun run() {
        println(currentThread().name)
        for (i in 0..10) {
            println(i)
            Thread.sleep(1000)
        }
    }
}

/**
 * Created 1000000 threads in 56980ms.
 * ....
 * Thread-49236
 * Thread-49237
 * Thread-49238
 * ....
 *
 */
fun testRunThread() {
    var counter = 0
    val numberOfThreads = 1_000_000
    val time = measureTimeMillis {
        for (i in 1..numberOfThreads) {
            thread {
                counter += 1
                println(currentThread().name)
            }
        }
    }
    println("Created ${numberOfThreads} threads in ${time}ms.")
}

/**
 * Created 1000000 threads in 551ms.
 * ...
 * main
 * main
 * ...
 *
 */
fun testRunCoroutine() {
    var counter = 0
    val numberOfThreads = 1_000_000
    val time = measureTimeMillis {
        for (i in 1..numberOfThreads) {
            CoroutineScope(context = Dispatchers.Unconfined).launch {
                counter += 1
                println(currentThread().name)
            }
        }
    }
    println("Created ${numberOfThreads} threads in ${time}ms.")
}