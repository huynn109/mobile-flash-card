package coroutine

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    doAsync()
}

/**
 * [async] Giống như launch{} hay runBlocking{}, tuy nhiên cho phép return Deferred<T>
 * [Deferred] hơn thằng [job] ngoài việc quản lý lifecycle còn mang cả giá trị trả về
 * [await] Để get cái trá trị trả về đó từ [Deferred]
 * [CoroutineStart.LAZY] Chỉ chạy coroutine khi gọi start. Nếu không gọi start nó sẽ chạy tuần tụ như runBlocking
 */
fun doAsync() = runBlocking {
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { printOne() }
        val two = async(start = CoroutineStart.LAZY) { printTwo() }
        one.start()
        two.start()
        println("The answer is ${one.await() + two.await()}")
    }
    println("Complete in $time ms")
}

suspend fun printOne(): Int {
    delay(1000)
    return 10
}

suspend fun printTwo(): Int {
    delay(1000)
    return 20
}