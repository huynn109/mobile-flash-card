package scope

fun main() {

    // Extension nên xài được cách này thôi
    val person = Person.personNull?.copy()?.doLet {
        println(it)
        it.incrementAge(2)
        println(it) // Return Unit
    }
    println(person)
}

/***
 * Let là extension function của generic <T>
 * Extension function này có tham số là 1 anonymous function
 * Anonymous function này có 1 tham số kiểu T và trả về kiểu R (Vì nhận tham số kiểu T nên trong block sẽ sử dụng it)
 * Let trả về kiểu giống với kiểu của anonymous function
 * Thường dùng để kiểm tra T có null hay không
 */
fun <T, R> T.doLet(block: (T) -> R): R {
    return block(this)
}

