package scope

fun main() {
    val person = Person.personNotNull.copy().doApply {
        println(this)
        this.moveTo("Cần Thơ")
        println(this) // Không cần return vì extension đã return Person
    }
    println(person)
}

/***
 * Là extension function của generic T
 * Nhận tham số truyền vào là một hàm mở rộng của generic T và không có kiểu trả về
 * Do có tham số truyền vào là hàm mở rộng T nên có thể sử dụng từ khóa "this" để gọi T
 * Kiểu trả về của extension Apply chính là generic T
 * Thường sử dụng để get/set cho property/method của T trước khi gán hoặc truyền vào hàm cho đối tượng khác
 */
fun <T> T.doApply(block: T.() -> Unit): T {
    block()
    return this
}