package zeng.fanda.com.kotlinpratice.reflect

import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberFunctions
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties

/**
 *
 * @author 曾凡达
 * @date 2019/11/12
 *
 */

fun main() {
//    remove(0)
    testKotlinClass()

//    testKCallable()
//    testFunctionN()
//    testKProperty()


}

// 顶层属性
var counter =0

fun testKProperty() {
    val kProperty = ::counter
//    kProperty.setter.call(42)
//    println(kProperty.call())

    kProperty.set(33)
    println(kProperty.get())

}

fun testFunctionN() {
    fun sum(x: Int, y: Int) = x + y

//    val function2 = ::sum
    // 显式声明类型
    val function2: Function2<Int, Int, Int> = ::sum
    println(function2.invoke(1, 2) + function2(3, 4))
}

fun testKCallable() {
    val kFunction = ::foo
//    val kFunction :Function1<Int,Unit> = ::foo

//    kFunction.call(44)
    kFunction.call()    //Callable expects 1 arguments, but 0 were provided.
}

@Deprecated("Use removeAt(index) instead.", ReplaceWith("removeAt(index)"))
fun remove(index: Int) {/**/
}

fun removeAt(index: Int) {/**/
}

fun test(list: List<*>) {
    @Suppress("UNCHECKED_CAST")
    val strings = list as List<String>
}

class Person(val name: String, val age: Int)

fun testKotlinClass() {
    val person = Person("fanda", 18)

//    val memberProperty = Person::age
    // 显式声明
    val memberProperty:KProperty1<Person,Int> = Person::age

    println(memberProperty.call(person))
    println(memberProperty.get(person))

//    val kClass = person.javaClass.kotlin
//    println(kClass.simpleName)
//    kClass.members.forEach { println(it) }
//    println()
//    kClass.memberProperties.forEach { println(it) }
//    println()
//    kClass.memberFunctions.forEach { println(it) }
}

fun foo(x: Int) = println(x)












