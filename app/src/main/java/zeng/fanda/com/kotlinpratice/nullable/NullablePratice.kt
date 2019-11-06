package zeng.fanda.com.kotlinpratice.nullable

import zeng.fanda.com.kotlinpratice.clazz.View

/**
 *
 * @author 曾凡达
 * @date 2019/10/29
 *
 */

fun main() {
    //Type mismatch.
//    strLen(x)   //不能把可空类型的值传给拥有非空类型参数的函数

//    testManagerName()
//    testPersonInfo()
//    Activity().otherMethod()

//    println(null.isNullOrBlank())
//    testBaby(Baby(null))


}

//fun strLen(s: String) = s.length
// Only safe (?.) or non-null asserted (!!.) calls are allowed on a nullable receiver of type String?
//fun strLen(s: String?) = s.length   // 不能直接调用它的方法

//fun strLen(s: String?) = if (s != null) s.length else 0
// elvis 运算符
fun strLen(s: String?) = s?.length ?: 0

/*fun foo(s: String?) {
    // 这样写会报错，不能把可空的类型赋值给非空类型
    val t: String = s
    //这样可以，因为 null 被合并检验了，如果是 null ，会返回 ""
    val t: String = s ?: ""
}*/


//val x : String? = null
//Type mismatch.
//val y: String = x   // 不能把它赋值给非空类型的变量

// 访问可空属性

class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

fun testManagerName() {
    val ceo = Employee("Fanda", null)
    val developer = Employee("liuhang", ceo)
    println(managerName(ceo))   // null
    println(managerName(developer))     // Fanda
}

class Address(val street: String, val city: String, val country: String)

class Company(val name: String, val address: Address?)

class Person(val name: String, val company: Company?)

/*fun Person.countryName(): String {
    // 使用多个安全调用,如果有属性为空，则返回空，不会报空指针，非常方便
    val countryName = this.company?.address?.country
    return if (countryName != null) countryName else "Unkown"
}*/

fun Person.countryName() = company?.address?.country ?: "Unkown"

fun printPersonInfo(person: Person) {
    // 如果为空，直接抛异常
    val address = person.company?.address ?: throw IllegalArgumentException("No address")

    // 用 with 函数避免重复引用
    with(address) {
        println(street)
        println("$city , $country")
    }
}

fun testPersonInfo() {
    val address = Address("chaguang", "shenzhen", "china")
    val company = Company("dudu", address)

//    val person = Person("fanda",company)

    // Exception in thread "main" java.lang.IllegalArgumentException: No address
    val person = Person("fanda", null)

    printPersonInfo(person)
}

fun ignoreNulls(s: String?) {
    // 如果 s 为 null ，这一行会报错
    val aNotNull: String = s!!  // s 做了非空断言，可以看作是非空类型的了
    println(aNotNull.length)
}

fun sendEmail(email: String) {
    println("Sending email to $email")
}

fun testSendEmail() {
    val email: String? = "fanda.com"
//    sendEmail(email)    // 不能直接这样写
    if (email != null) sendEmail(email)     //先做校验，才能当作非空类型使用

    email?.let { sendEmail(it) }    // 通过安全调用 let 函数
//    email?.let { email -> sendEmail(email) }    // 通过安全调用 let 函数
}

class Activity {
    lateinit var view: View

    fun onCreate() {
        view = View()
    }

    fun otherMethod() {
        // 需要这样调用，非常麻烦
        view.onClickListener()
    }
}

class Activity2 {
    var view: View? = null

    fun onCreate() {
        view = View()
    }

    fun otherMethod() {
        // 需要这样调用，非常麻烦
        view?.onClickListener()
    }
}

fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {    // 该方法是 String? 的扩展方法，不需要安全调用
        println("Please fill in the required fields")
    }
}

/*fun <T> printHashCode(t: T) {
    // 需要安全调用
    println(t?.hashCode())
}*/

// 给类型参数设置上界
fun <T : Any> printHashCode(t: T) {
    // 不需要安全调用
    println(t.hashCode())
}

fun testBaby(baby: Baby) {
    //java.lang.IllegalStateException: baby.name must not be null
    println(baby.name.toUpperCase())    // 如果为Null，则抛异常
    println(baby.name?.toUpperCase())   // 安全调用

    val name: String = baby.name    // 看作非空类型
    val name2: String? = baby.name  // 看作可空类型
}

class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        println(value ?: "")
    }
}




