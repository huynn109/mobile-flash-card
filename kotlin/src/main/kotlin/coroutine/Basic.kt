package coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

suspend fun main() {
    sayHello()
    doRunBlocking()
    println("${measureTimeMillis(::launchMultiCoroutine)}")
}

fun launchCoroutine() {
    GlobalScope.launch { // chạy một coroutine trên background thread
        delay(1000) // non-blocking coroutine bị delay 1s
        println("World,") // print từ World ra sau khi hết delay
    }
    println("Hello,") // main thread vẫn tiếp tục chạy xuống dòng code này trong khi coroutine vẫn đang bị delay 1s
    Thread.sleep(2000) // block main thread 2s
    println("Coroutine")
}

/**
 * Suspend hoặc coroutine mới call được mấy thằng suspend nhe. Vì thằng [delay] là thằng suspend
 * Không chịu thêm suspend thì bị báo lỗi đỏ lè như này ok?
 * Error: Kotlin: Suspend functions are only allowed to be called from a coroutine or another suspend function
 */
suspend fun sayHello() {
    delay(1000)
    println("Hello coroutine")
}

/**
 * Muốn chạy blocking thì như này [runBlocking]
 */
private fun doRunBlocking() {
    runBlocking {
        println("Run")
        delay(2000)
    }
    println("blocking")
}

/**
 * Test thử coi chạy nhanh ko?
 */
private fun launchMultiCoroutine() = runBlocking {
        repeat(100_000) {
            GlobalScope.launch {
            }
        }
    }