import creational.*

fun main(args: Array<String>) {
    Singleton.getInstance().hello("singleton")
    Singleton1.getInstance().hello("singleton")
    Singleton2.getInstance().hello("singleton")
    Singleton3.getInstance.hello("singleton")
    Singleton4.hello("singleton")
    Singleton5.getInstance("param").hello("singleton")
}