package zeng.fanda.com.kotlinpratice.height

import zeng.fanda.com.kotlinpratice.lambda.Person
import java.io.BufferedReader
import java.io.FileReader
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

/**
 *
 * @author 曾凡达
 * @date 2019/11/6
 *
 */

fun main() {
//    testTwoAndThree()
//    println("ab.id390lj".filter { it in 'a'..'z' }) //abidlj
//    testGetShippingCostCalculator() //Shopping costa 12.3
//    getWindowsAverage()
//    println(log.averageDurationFor(OS.MAC))
//    getMobileAverage()
//    getMobilePathAverage()

//    println(log.averageDuration { it.os == OS.MAC })
//    println(log.averageDuration { it.os == OS.MAC && it.path == "/signup" })

/*    synchronized(ReentrantLock()) {
        println(42)
    }*/

/*    ReentrantLock().withLock {
        println(42)
    }*/

//    lookForAlice()
//    lookForAliceForEach()
    lookForAliceForEachNoName()
    lookForAliceForEachWithLable()
//    testApplyThis()
}

fun <T> Lock.withLock(action: () -> T): T {
    lock()
    try {
        return action()
    } finally {
        unlock()
    }
}

fun readFirstLineFormFile(path: String): String {
    BufferedReader(FileReader(path)).use { br -> return br.readLine() }
}

fun createHeightFun1() {

    // 通过类型推导
//    val sum = { x: Int, y: Int -> x + y }
//    val action = { println(42) }

    // 显式声明
    val sum: (Int, Int) -> Int = { x, y -> x + y }
    val action: () -> Unit = { println(42) }

    var canReturnNull: (Int, Int) -> Int? = { _, _ -> null }

    var funOrNull: ((Int, Int) -> Int)? = null

}

// 给函数类型的参数指定了名字
fun performRequest(url: String, callback: (code: Int, content: String) -> Unit) {
    /*..*/
}

fun testPerformRequest() {
    val url = "http://test.com"

    // 使用定义的名字
    performRequest(url, { code, content -> /*..*/ })
    // 改变参数的名字
    performRequest(url, { x, y -> /**/ })
}

fun twoAndThree(operation: (Int, Int) -> Int) {
//    print(operation.invoke(2,3))
    print(operation(2, 3))
}

fun testTwoAndThree() {
    twoAndThree { x, y -> x + y }
    twoAndThree { x, y -> x * y }
}

fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    forEach { if (predicate(it)) sb.append(it) }
    return sb.toString()
}

fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}

fun foo(callback: (() -> Unit)?) {
    if (callback != null) {
        callback()
    }

    // 这种方式也可以调用
    callback?.invoke()
}

// 返回函数的函数

enum class Delivery { STANDARD, EXPEDITED }
class Order(val itemCount: Int)

fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount }
    }
    return { order -> 1.2 * order.itemCount }
}

fun testGetShippingCostCalculator() {
    // 根据不同的枚举类型，返回指定的函数
    val calculator = getShippingCostCalculator(Delivery.EXPEDITED)
    // 调用函数获取数据
    println("Shopping costa ${calculator(Order(3))}")
}

// 通过 lambda 去除重复的代码

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

data class SiteVisit(val path: String, val duration: Double, val os: OS)

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

fun getWindowsAverage() {
    val averageWindowsDuration = log.filter { it.os == OS.WINDOWS }.map(SiteVisit::duration).average()
    println(averageWindowsDuration) //23.0
}

fun getMobileAverage() {
    val averageWindowsDuration =
        log.filter { it.os == OS.ANDROID || it.os == OS.IOS }.map(SiteVisit::duration).average()
//    val averageWindowsDuration = log.filter { it.os in setOf(OS.IOS, OS.ANDROID) }.map(SiteVisit::duration).average()
    println(averageWindowsDuration) //12.15
}

fun getMobilePathAverage() {
    val averageWindowsDuration =
        log.filter { it.os == OS.IOS && it.path == "/signup" }.map(SiteVisit::duration).average()
    println(averageWindowsDuration) //12.15
}

// 作为扩展函数，将平台类型抽为参数
fun List<SiteVisit>.averageDurationFor(os: OS) = filter { it.os == os }.map(SiteVisit::duration).average()

// 作为扩展函数，将判断式抽为函数类型的参数
fun List<SiteVisit>.averageDuration(predicate: (SiteVisit) -> Boolean) {
    filter(predicate).map(SiteVisit::duration).average()
}


// 内联函数
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        synchronized(lock, body)
    }
}

// params1 被内联，params2 被标记不需要内联
inline fun foo(params1: () -> Unit, noinline params2: () -> Unit) {
}

// 高阶函数中的控制流


fun lookForAlice() {
    val personList = listOf(Person("Alice", 29), Person("fanda", 18))
    for (p in personList) {
        if (p.name == "Alice") {
            // 正常的函数返回
            return println("Found!")
        }
    }
    println("Alice is not found !")
}

fun lookForAliceForEach() {
    val personList = listOf(Person("Alice", 29), Person("fanda", 18))
    personList.forEach label@{
        if (it.name == "Alice") {
            // 在 lambda 中返回
//            return println("Found!")

            //从标签返回
            return@label
        }
    }
    // 标签返回之后，会执行这里的代码
    println("Alice is not found !")
}

fun lookForAliceForEachNoLable() {
    val personList = listOf(Person("Alice", 29), Person("fanda", 18))
    personList.forEach {
        if (it.name == "Alice") {
            //从标签返回
            return@forEach
        }
    }
    // 标签返回之后，会执行这里的代码
    println("Alice is not found !")
}

fun lookForAliceForEachNoName() {
    val personList = listOf(Person("Alice", 29), Person("fanda", 18))
    personList.forEach(fun(p) {
        if (p.name == "Alice") return
        println("${p.name} is not Alice")
    })
}

fun lookForAliceForEachWithLable() {
    val personList = listOf(Person("Alice", 29), Person("fanda", 18))
    personList.forEach {
        if (it.name == "Alice") return@forEach
        println("${it.name} is not Alice")
    }

    personList.filter(fun(p): Boolean {
        return p.age < 30
    })

    personList.filter(fun(p) = p.age < 30)

    personList.filter { p -> p.age < 30 }
    personList.filter { p: Person -> p.age < 30 }
    personList.filter({ p: Person -> p.age < 30 })

}

fun testApplyThis() {
    println(StringBuffer().apply sb@{
        listOf(1, 2, 3).apply {
            this@sb.append(this.toString())
        }
    })
}














