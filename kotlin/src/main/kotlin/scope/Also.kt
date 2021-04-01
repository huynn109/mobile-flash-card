package scope

fun main() {
    val person = Person.personNotNull.copy().doAlso { person ->
        changeAge(person)
    }
    println(person)
}

fun changeAge(it: Person) {
    it.age = 4
}

/**
 * Là extension của generic T
 * Nhận tham số truyền vào là anonymous function -> nên có thể sử dụng "it" để gọi T
 * Anonymous function có tham số là generic T và không có kiểu trả về
 * Kiểu trả về của Also chính là T
 * Thường dùng để check null của T đặc biệt khi T là tham số của hàm khác define tên sẽ rõ ràng hơn là this
 */
fun <T> T.doAlso(block: (T) -> Unit): T {
    block(this)
    return this
}