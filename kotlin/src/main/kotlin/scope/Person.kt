package scope


data class Person(val name: String, var age: Int, var position: String) {
    companion object {
        var personNull: Person? = null
        var personNotNull = Person("chuột", 1, "sài gòn")
    }

    fun moveTo(position: String) {
        this.position = position
    }

    fun incrementAge(age: Int) {
        this.age = age
    }
}