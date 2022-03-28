package inline

class NoInlineClassName(private val s: String) {
    init {
        require(s.isNotEmpty()) { }
    }

    val length: Int
        get() = s.length

    fun greet() {
        println("Hello, $s")
    }
}

/*
String name = Name.constructor-impl("Kotlin");
      Name.greet-impl(name);
      int var1 = Name.getLength-impl(name);
      System.out.println(var1);


 */
fun main() {
    val name = NoInlineClassName("Kotlin")
    name.greet()
    println(name.length)
}