package coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

fun main() {
//    doRunCoroutineChild()
//    doCancelCoroutineParent()
    doGlobalScope()
}

/***
 * Để quản lý toàn bộ coroutine trong scope
 * [launch] và [async] là extension của [CoroutineScope]
 * [runBlocking] có param là [CoroutineScope] nên sử dụng được bên ngoài CoroutineScope
 * Ngoài vùng [CoroutineScope] thì không thể launch coroutine
 */
fun doCoroutineSCope() {
    CoroutineScope(context = Dispatchers.IO).launch {

    }
    runBlocking { }
}

/**
 * Có thêm 2 cách để launch 1 coroutine là
 * - Tự custome coroutine với delegate
 * - Extend interface CoroutineScope
 */
class CustomCoroutineScope : CoroutineScope by CoroutineScope(Dispatchers.Default)
class CustomCoroutineScope1 : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default
}

/**
 * [coroutineScope] builder giống như runBlocking tuy nhiên nó là 1 suspend function nên chỉ chạy trong 1 suspend function
 * Coroutine cha phải đợi mấy thằng coroutine con bên trong nó chạy xong nó mới chạy tiếp xuống dưới nên
 * "Task from coroutine scope" in ra đầu tiên vì delay 100
 * "Task from runBlocking" in ra thứ 2 vì delay 200
 * "Task from nested launch" in ra thứ 3 vì delay 500
 * 3 thằng trên đều là con của coroutine scope [runBlocking] nên phải đợi nó chạy xong
 * Xong rồi mới chạy tiếp xuống thằng "Coroutine scope is over"
 */
fun doRunCoroutineChild() = runBlocking {
    launch {
        delay(200)
        println("Task from runBlocking")
    }
    coroutineScope {
        launch {
            delay(500)
            println("Task from nested launch")
        }
        delay(100)
        println("Task from coroutine scope")
    }
    println("Coroutine scope is over")
}

/**
 * Coroutine cha cancel ở 500ms nên toàn bộ coroutine con bị hủy nên không thể in ra "line code 2"
 */
fun doCancelCoroutineParent() = runBlocking<Unit> {
    val request = launch {
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")   // line code 1
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled") // line code 2
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000)
    println("main: Who has survived request cancellation?") // line code 3
}

/**
 * Nếu thằng cha bị hủy mà thằng con là GlobalScope thì nó sẽ ko hủy. Vẫn chạy bất chấp
 */
fun doGlobalScope() = runBlocking {
    val request = launch {
        // it spawns two other jobs, one with GlobalScope
        GlobalScope.launch {
            println("job1: GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation")  // line code 1 này vẫn được in ra mặc dù bị delay 1000ms
        }
        // and the other inherits the parent context
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // cancel processing of the request
    delay(1000) // delay a second to see what happens
    println("main: Who has survived request cancellation?")
}

/**
 * Sử dụng [CoroutineScope] để quản lý vòng đời theo vòng đời của Activity/Fragment/ViewModel để tránh memory leak
 * Ngoài ra còn có [viewModelScope] là ext trong dependency ktx sẽ tự động cancel tất cả coroutine khi [viewModel] bị clear mà không cần gọi
 */
class Activity : CoroutineScope by CoroutineScope(Dispatchers.Default) {
    fun destroy() {
        cancel() // Extension on CoroutineScope
    }
    fun doSomething() {
        // launch ten coroutines for a demo, each working for a different time
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // variable delay 200ms, 400ms, ... etc
                println("Coroutine $i is done")
            }
        }
    }
}

