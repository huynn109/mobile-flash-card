package scope

fun main() {
    val person = Person.personNotNull.doRun {
        position = "Nha Trang"
        incrementAge(3)
        return@doRun this
    }
    println(person)
}

/***
 * Là extension của generic T
 * Nhận tham số truyền vào là hàm mở rộng của generic T và trả về kiểu R
 * Do nhận tham số là hàm mở rộng của T nên có thể sử dụng "this" để gọi T
 * Kiểu trả về của Run giống với kiểu trả về của block
 * Thường dùng để chạy nhìu lệnh của T
 */
fun <T, R> T.doRun(block: T.() -> R): R {
    return block()
}