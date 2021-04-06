package coroutine

import kotlinx.coroutines.*

fun main() {
//    doExceptionLaunch()
//    doExceptionAsync()
//    doCatch()
//    doCoroutineExceptionHandle()
//    doSuperVision()
    doSuperVisionScope()
}

/**
 * Nếu [launch] thì nó sẽ quăng throw ngay khi gặp exception
 */
fun doExceptionLaunch() = runBlocking {
    launch {
        println("First exception")
        throw IndexOutOfBoundsException()
        println("Last exception")
    }
}

/**
 * Nếu launch [async] thì khi nào awai mới quăng throw vì nó được đóng gói trong deferred
 */
fun doExceptionAsync() = runBlocking {
    val deferred = GlobalScope.async {
        println("First exception")
        throw IndexOutOfBoundsException()
        println("last exception")
    }
    deferred.await()
}

fun doCatch(handle: CoroutineExceptionHandler) = runBlocking {
    GlobalScope.launch(handle) {
//        try {
            println("Throwing exception from launch")
            throw IndexOutOfBoundsException()
            println("Unreached")
//        } catch (e: IndexOutOfBoundsException) {
//            println("Caught IndexOutOfBoundsException")
//        }
    }

    val deferred = GlobalScope.async(handle) {
        println("Throwing exception from async")
        throw ArithmeticException()
        println("Unreached")
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }
}

/**
 * [CoroutineExceptionHandler] không bắt đc throw trong async vì nó đóng gói trong deferred nên phải tự handle
 * Các exception được bắt sẽ trả về hàm handleException(context: CoroutineContext, exception: Throwable)
 */
fun doCoroutineExceptionHandle() = runBlocking {
    val handler = CoroutineExceptionHandler { context, exception ->
        println("Caught exception handle $exception")
    }
    doCatch(handler)
}

/**
 * [SupervisorJob] xài Job này để khi các coroutine con xảy ra những thằng coroutine con khác vẫn chạy tiếp
 */
fun doSuperVision() = runBlocking {
    val supervisor = SupervisorJob()
    with(CoroutineScope(coroutineContext + supervisor)) {
        // launch the first child -- its exception is ignored for this example (don't do this in practice!)
        val firstChild = launch(CoroutineExceptionHandler { _, _ ->  }) {
            println("First child is failing")
            throw AssertionError("First child is cancelled")
        }
        // launch the second child
        val secondChild = launch {
            firstChild.join()
            // Cancellation of the first child is not propagated to the second child
            println("First child is cancelled: ${firstChild.isCancelled}, but second child is still active")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                // But cancellation of the supervisor is propagated
                println("Second child is cancelled because supervisor is cancelled")
            }
        }
        // wait until the first child fails & completes
        firstChild.join()
        println("Cancelling supervisor")
        supervisor.cancel()
        secondChild.join()
    }
}

/**
 * Dùng thằng này launch coroutine tác dụng tương tự như [SupervisorJob]
 * Lưu ý các exception của con không được truyền đến thằng cha
 */
fun doSuperVisionScope() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    supervisorScope {
        val first = launch(handler) {
            println("Child throws an exception")
            throw AssertionError()
        }
        val second = launch {
            delay(100)
            println("Scope is completing")
        }
    }
    println("Scope is completed")
}