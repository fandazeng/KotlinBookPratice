package zeng.fanda.com.kotlinpratice.datatype

import zeng.fanda.com.kotlinpratice.nullable.Person
import java.io.BufferedReader

/**
 *
 * @author 曾凡达
 * @date 2019/10/31
 *
 */

fun main() {
//    showProgress(-34)   //0% done
//    showProgress(300)   //100% done

//    testBaseDataType(1)
//    testFoo()
//    method(1)
//    testFilterNotNull()
//    testArray()
//    createAZ()
    transformToArray()
}

fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("$percent% done")
}

fun testBaseDataType(params: Int): Int {
    val i: Int = 2
//    val l: Long = i // 不能直接这样赋值，不会自动转换，会报错
    val l: Long = i.toLong() // 需要显式转换

    val list: List<Int> = listOf(1, 2, 3)

    val f = .45     //省略前面的0 ，其实就是 0.45
    print(f)

    val longList = listOf(1L, 2L, 3L)
//    println(i in longList)	//假设支持隐式转换，结果会返回 false
    println(i.toLong() in longList)     // 返回 true
    return i
}

class Man(val age: Int? = null) {

    fun isOlderThan(other: Man): Boolean? {
        // 先校验
        if (age == null || other.age == null) {
            return null
        }
        // 当做非空类型来处理
        return this.age > other.age
    }
}

fun foo(l: Long) = println(l)

fun testFoo() {
    val i = 100
    val b: Byte = 1
    val l = b + 1L  // //Byte + Long -> Long，运算符被重载，会自动处理

    foo(42)     // 通过数字字面值，不用显式转换
    foo(i.toLong())     // 不是通过数字字面值，需要显式转换

    "3".toInt()     // 字符串有一整套扩展方法来尝试转换成基本类型

}

fun method(params: Any): Any {
    val d = Unit
    println(d)
    return params
}

//fun testUnit():Unit {}
//fun testUnit() {}   // 省略 Unit


interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    override fun process() {
        //do something
        // 不需要显式调用 return
    }
}

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun Person.countryName() {
    val address = company?.address ?: fail("No address")
    println(address)
}

fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()  //创建包含可空 int 值的列表
    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number)  // 添加非空值
        } catch (e: NumberFormatException) {
            result.add(null)    // 添加 null 值
        }
    }
    return result
}

fun testFilterNotNull() {
    val list = listOf(1, null, 4, "fanda")
    println(list)   //[1, null, 4, fanda]

    val result = list.filterNotNull()   //过滤操作，变成非空类型的了
    println(result)   //[1, 4, fanda]
}

fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {  // 读取 source 的元素
        target.add(item)    // 将元素添加到可变集合 target
    }
}

/*class TestInterfaceImpl1 : TestInterface {

    // 集合是否可空
    override fun test(values: MutableList<String>?) {}
    override fun test(values: MutableList<String>) {}

    // 元素是否可空
    override fun test(values: MutableList<String>) {}
    override fun test(values: MutableList<String?>) {}

    // 方法会不会修改集合
    override fun test(values: MutableList<String>) {}
    override fun test(values: List<String>) {}

}*/

fun testArray() {
    val testStringArray = Array<String>(3) { "init" }    // 带初始化数量的数组，且给定初始化值

    println(testStringArray.size)   //5

    for (i in testStringArray.indices) {    // 遍历数组的下标
        println("index is $i and value is ${testStringArray[i]}")
    }

    // 没有初始化值，不能通过类型推导知道，要加上类型参数
    val nullableArray = arrayOfNulls<String>(3)
}

fun createAZ() {
    // it 代表每个数组元素的下标，lambda 会生成对应的下标对应的值
    val result = Array(26) { ('a' + it).toString() }
    // ABCDEFGHIJKLMNOPQRSTUVWXYZ
    println(result.joinToString("") { it.toUpperCase() })
}

fun transformToArray() {
    val content = listOf("a", "b", "c")
    // toTypedArray 用来给集合转成对应数组
    println("%s/%s/%s".format(*content.toTypedArray())) //a/b/c

    val a = IntArray(3)
    val b = intArrayOf(0, 0, 0)
    val c = IntArray(3) { it * it }

}







