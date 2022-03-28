package inline

@JvmInline
value class Name(private val s: String) {
    init {
        require(s.isNotEmpty()) { }
    }

    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}

fun main() {
    val name = Name("Kotlin")
    name.greet() // ~~~>decompile java static function
    println(name.length) // ~~~>decompile java static property
}