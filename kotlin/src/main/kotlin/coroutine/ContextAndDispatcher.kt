package coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/***
 * Mỗi coroutine đều có một context là một instance của interface [CoroutineContext]
 * Context là tập hợp các element để config cho coroutine
 * [Job]: lifecycle của coroutine, hủy coroutine
 * [CoroutineName] đặt tên cho coroutine
 * [NonCancellable] never die, gọi cancel cũng k die =)))
 * [Dispatcher] quyết định chạy thread nào
 * [Dispatcher.Default] chạy background của thread pool
 * [Dispatcher.IO] chạy background của thread pool
 * [Dispatcher.Main] chạy trên Main thread
 * [Dispatcher.newSingleThreadContext] chạy trên thread tự đặt tên
 * [Dispatcher.newFixedThreadPoolContext] chạy theo số thread quy định trong shared background thread pool
 */
fun main() {
    getCoroutineContext()
}

/**
 * Có thể thêm các element bằng toán tử "+"
 */
@ObsoleteCoroutinesApi
fun plusElement() {
    runBlocking { Dispatchers.IO + Job() }
    GlobalScope.launch {
        // tương đương với GlobalScope.launch (Dispatchers.Default + Job()) { }
    }
    // hoặc set context khi sử dụng launch { } để start coroutine
    GlobalScope.launch(newSingleThreadContext("demo_thread") + CoroutineName("demo_2") + NonCancellable) {

    }
}

/**
 * Get context thông qua property coroutineContext
 */
fun getCoroutineContext() = runBlocking<Unit> {
    println("My context is: ${coroutineContext + CoroutineName("add_name")}")
    println("My context is: $coroutineContext")
}

