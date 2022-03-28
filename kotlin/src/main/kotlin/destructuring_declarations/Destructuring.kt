package destructuring_declarations

fun main() {
    val map = mapOf(1 to 2, 2 to 3)
    val person = Person()
    val personDataClass = DestructuringPerson()
    val (a, b) = person
    val (c, d) = personDataClass
    for ((key, value) in map) {
        println("$key -> $value")
    }
    println(a)
    println(b)
    println(c)
    println(d)
}

class Person {
    operator fun component1(): String {
        return name
    }

    operator fun component2(): String {
        return age
    }

    val name: String = "A Khánh"
    val age: String = "2k"

}

