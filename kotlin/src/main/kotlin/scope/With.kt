package scope

fun main() {
    // Xài như này
    val person = doWith(receiver = Person.personNotNull.copy(), block = {
        this.moveTo("Cần Thơ")
        println(this.position) // Return Unit
    })

    // Hoặc có thể viết gọn hơn như này
    val person1 = doWith(Person.personNotNull.copy()) {
        this.moveTo("Nha Trang")
        return@doWith this // Return Person
    }
    println(person) // Return Unit
    println(person1) // Return person
}

/***
 * Không phải là extension mà chỉ là hàm bth
 * Nhận 2 tham số: receiver là tham số kiểu T, block là hàm mở rộng của T và có kiểu trả về là R
 * Kiểu trả về của With chính là kiểu trả về của hàm mở rộng (block)
 * Vì là hàm mở rộng của lớp T nên có thể sử dụng từ khóa this để gọi T
 * Thường dùng để get property/method của T để thực hiện 1 công việc nào đó
 */
fun <T, R> doWith(receiver: T, block: T.() -> R): R {
    return receiver.block()
}