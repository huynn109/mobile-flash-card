package coroutine

import kotlinx.coroutines.*

fun main() {
//    doExceptionLaunch()
//    doExceptionAsync()
//    doCatch()
    doCoroutineExceptionHandle()
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