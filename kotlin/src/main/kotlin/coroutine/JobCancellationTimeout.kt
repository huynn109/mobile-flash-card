package coroutine

import kotlinx.coroutines.*

fun main() {
//    doJoin()
//    doCancel()
//    doFinally()
//    doCoroutineNeverDie()
    doTimeout()
}

/**
 * [Job] là một element nắm giữ lifecycle của Coroutine
 * [launch] return Job
 */
fun doJobs(): Job {
    return GlobalScope.launch {
        delay(2000)
        println("Hello Jobs")
    }
}

fun doJoin() = runBlocking {
    val job = doJobs()
    println("Hello,")
    job.join()
    println("Coroutine")
}

/**
 * [cancel] Dùng để set lại property [isActive]
 * Tất cả các suspend function trong coroutine đều kiểm tra [isActive] để dừng lại tiến trình đang chạy
 */
fun doCancel() = runBlocking {
    val startTime = System.currentTimeMillis()
    println("current Thread - ${Thread.currentThread().name}")
    val job = launch(Dispatchers.Default) {
        println("current Thread - ${Thread.currentThread().name}")
        var nextPrintTime = startTime
        var i = 0
        while (isActive) {
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300)
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    println("current Thread - ${Thread.currentThread().name}")
    println("main: Now I can quit.")
}

/**
 * Dù có [cancel] thì finally vẫn chạy sau khi coroutine bị hủy
 */
fun doFinally() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm running $i ...")
                delay(500)
            }
        } finally {
            println("I'm finally")
            delay(1000) // Do hàm này kiểm tra isActive nên nó hủy ngay lập tức tiến trình đang chạy k chạy xuống dưới nữa
            println("I'm last finally")
        }
    }
    delay(1300)
    println("I'm waiting")
    job.cancel()
    println("I'm quit")
}

/**
 * Element [NonCancellable] sẽ giúp cho coroutine bất tử :D. Chạy nào mệt nghỉ
 */
fun doCoroutineNeverDie() = runBlocking {
    println("current thread - ${Thread.currentThread().name}")
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm running $i ...")
                delay(500)
            }
        } finally {
            withContext(NonCancellable) {
                println("current thread - ${Thread.currentThread().name}")
                println("Never die 1")
                delay(1000)
                println("current thread - ${Thread.currentThread().name}")
                println("Never die 2")
            }
        }
    }
    delay(1300)
    println("current thread - ${Thread.currentThread().name}")
    println("I'm tired of waiting!")
    job.cancel() // cancels the job
    println("Now I can quit.")
}

/**
 * [withTimeout] Quăng ra [TimeoutCancellationException] nếu hết giờ mà chưa chạy xong
 * [withTimeoutOrNull] return [null] nếu hết giờ mà chưa chạy xong
 */
fun doTimeout() = runBlocking {
    val result = withTimeoutOrNull(1300) {
        repeat(1000) { i ->
            println("I'm running $i ...")
            delay(500)
        }
        "Done"
    }
    println("result $result")
}