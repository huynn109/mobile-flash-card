package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlin.system.measureTimeMillis

fun main() {
//    doCollection()
//    doSequence()
//    println(fibonacciSequence().take(9).toList())
//    fibonacciCollection(10)
    println(fibonacciNormal(9))
//    println(fibonacciRecursive(9))
    println(fibonacciDynamicProgramming(9))
//    doFlow()
//    doSequenceBlock()
//    doFlowNotBlock()
//    doFlowCold()
//    doCancelFlow()
}

fun fibonacciSequence() = sequence {
    var terms = Pair(0, 1)
    // this sequence is infinite
    while (true) {
        yield(terms.first)
        terms = Pair(terms.second, terms.first + terms.second)
    }
}

fun fibonacciNormal(n: Int): Int {
    var a = 0
    var b = 1
    var c: Int
    var i: Int
    if (n == 0) return a
    else {
        i = 2
        while (i <= n) {
            c = a + b
            a = b
            b = c
            i++
        }
    }
    return b
}

fun fibonacciRecursive(n: Int): Int {
    return if (n <= 1)
        n
    else fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2)
}

fun fibonacciDynamicProgramming(n: Int): Int {
    val tmp = IntArray(n + 1)
    tmp[0] = 0
    tmp[1] = 1
    for(i in 2..n){
        tmp[i] = tmp[i - 1] + tmp[i - 2]
    }
    return tmp[n]
}

fun fibonacciCollection(n: Int) {
    var terms = Pair(0, 1)
    // this sequence is infinite
    var i = 0
    while (i < 10) {
        i++
        println(terms.first)
        terms = Pair(terms.second, terms.first + terms.second)
    }
}

suspend fun fooCollection(): List<Int> {
    val list = mutableListOf<Int>()
    for (i in 1..3) {
        delay(1000)
        list.add(i)
    }

    return list
}

fun fooSequence(): Sequence<Int> = sequence {
    for (i in 1..3) {
        Thread.sleep(1000)
        yield(i)
    }
}

fun fooFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(1000)
        emit(i)
    }
}

fun doCollection() = runBlocking {
    val time = measureTimeMillis {
        fooCollection().forEach { println(it) }
    }
    println(time)
}

fun doSequence() = runBlocking {
    val time = measureTimeMillis {
        fooSequence().forEach { value -> println(value) }
    }
    println(time)
}

@FlowPreview
fun doFlow() = runBlocking {
    val time = measureTimeMillis {
        fooFlow().collect { value -> println(value) }
    }
    println(time)
}

/**
 * [Sequence] Block main thread nên phải đợi sequence chạy xong mới in "I'm blocked"
 * [Sequence] Xử lý đồng bộ
 */
fun doSequenceBlock() = runBlocking {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        println(Thread.currentThread().name)
        for (k in 1..3) {
            delay(1000)
            println("I'm blocked $k")
        }
    }
    val time = measureTimeMillis {
        fooSequence().forEach { value -> println(value) }
    }
    println("$time s")
}

/**
 * [Flow] Không block main thread nên nó in ra "I'm blocked" song song với flow
 * [Flow] Xử lý bất đồng bộ
 * Emit dùng để emit các giá trị. Hàm này là suspend function
 * Collect dùng để get các giá trị emit. Hàm này là suspend function
 */
fun doFlowNotBlock() = runBlocking {
    // Launch a concurrent coroutine to check if the main thread is blocked
    launch {
        println(Thread.currentThread().name)
        for (k in 1..3) {
            delay(900)
            println("I'm not blocked $k")
        }
    }
    // Collect the flow
    val time = measureTimeMillis {
        fooFlow().collect { value -> println(value) }
    }
    println("$time s")
}

/**
 * Flow sẽ không chạy cho đến khi gọi [collect]
 */
fun doFlowCold() = runBlocking<Unit> {
    println("Calling foo...")
    val flow = fooFlow()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}

fun flowCancel(): Flow<Int> = flow {
    for (i in 1..3) {
        Thread.sleep(2000)
        println("Emitting $i")
        emit(i)
    }
}

/**
 * [Flow] Bị cancel khi timeout
 */
fun doCancelFlow() = runBlocking {
    withTimeoutOrNull(5000) {
        flowCancel().collect { println(it) }
    }
    println("Done")
}