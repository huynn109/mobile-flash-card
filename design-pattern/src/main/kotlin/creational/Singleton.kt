package creational

/**
 * Pattern này đảm bảo việc chỉ có duy nhất một instance được tạo ra
 */
class Singleton private constructor() {
    companion object {
        private var INSTANCE: Singleton? = null
        fun getInstance(): Singleton {
            if (INSTANCE == null) INSTANCE = Singleton()
            return INSTANCE!!
        }
    }

    fun hello(s: String) = println(s)
}

class Singleton1 private constructor() {
    private object Holder {
        val INSTANCE = Singleton1()
    }

    companion object {
        @JvmStatic
        fun getInstance(): Singleton1 {
            return Holder.INSTANCE
        }
    }

    fun hello(s: String) = println(s)
}

class Singleton2 private constructor() {

    companion object {
        @Volatile
        private var INSTANCE: Singleton2? = null
        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Singleton2().also { INSTANCE = it }
        }
    }

    fun hello(s: String) = println(s)
}

class Singleton3 private constructor() {

    companion object {
        val getInstance = Singleton3()
    }

    fun hello(s: String) = println(s)
}

object Singleton4 {
    fun hello(s: String) = println(s)
}

class Singleton5 private constructor(var param: String) {

    companion object {
        private var INSTANCE: Singleton5? = null
        fun getInstance(param: String) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Singleton5(param = param).also { INSTANCE = it }
        }
    }

    fun hello(s: String) = println("$s $param")
}
