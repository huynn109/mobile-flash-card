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
@ObsoleteCoroutinesApi
fun main() {
//    getCoroutineContext()
//    doWithContext()
//    doDispatcher()
//    doDispatcherUnconfined()
    setNameCoroutine()
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

/**
 * WithContext dùng để with giữa các context của Coroutine(chuyển đổi thread chỉ là 1 phần vì một element sẽ đươc chạy trên thread quy định)
 * VD từ background lên UI để update UI
 */
@ObsoleteCoroutinesApi
fun doWithContext() {
    newSingleThreadContext("thread1").use { ctx1 ->
        println("ctx1 - ${Thread.currentThread().name}")
        newSingleThreadContext("thread2").use { ctx2 ->
            println("ctx2 - ${Thread.currentThread().name}")
            runBlocking(ctx1) {
                println("started in ctx1 - ${Thread.currentThread().name}")
                withContext(ctx2) {
                    println("started in ctx2 - ${Thread.currentThread().name}")
                }
                println("back to ctx1 - ${Thread.currentThread().name}")
            }
            println("out of ctx2 bloc - ${Thread.currentThread().name}")
        }
        println("out of ctx1 bloc - ${Thread.currentThread().name}")
    }
}

/**
 * [Unconfined] Xem hàm bên dưới
 * [Dispatchers.Default] Chạy background thread
 * [Dispatchers.IO] Chạy background thread
 * [newSingleThreadContext] Chạy background thread
 */
@ObsoleteCoroutinesApi
fun doDispatcher() = runBlocking<Unit> {
    launch(Dispatchers.Unconfined) {
        println("Unconfined - ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        println("Default - ${Thread.currentThread().name}")
    }
    launch(Dispatchers.IO) {
        println("IO - ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("newSingleThreadContext")) {
        println("newSingleThreadContext - ${Thread.currentThread().name}")
    }
}

/**
 * [Dispatchers.Unconfined] Dispatcher này không chạy trên một thread cụ thể nào cả nó sẽ chạy theo current thread của coroutineScope
 */
fun doDispatcherUnconfined() = runBlocking {
    launch(Dispatchers.Unconfined) {
        println("Unconfined - ${Thread.currentThread().name}")
        delay(1000)
        println("Unconfined - ${Thread.currentThread().name}")
    }
}

/**
 * Đặt tên cho coroutine dùng [CoroutineName]
 */
fun setNameCoroutine() = runBlocking {
    launch(CoroutineName("name_coroutine")) {
        println("Coroutine name - $coroutineContext")
    }
}

