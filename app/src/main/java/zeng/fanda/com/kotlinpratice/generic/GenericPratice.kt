package zeng.fanda.com.kotlinpratice.generic

import zeng.fanda.com.kotlinpratice.clazz.Animal
import zeng.fanda.com.kotlinpratice.clazz.Cat
import java.util.*

/**
 *
 * @author 曾凡达
 * @date 2019/11/8
 *
 */

fun main() {

//    testGenericType()
//    println(listOf(1, 2, 3).penultimate)      //2
//    testPrintSum()
//    println(isA<String>("abc")) // true
//    println(isA<String>(123))//false
    // [abc, fanda]
//    println(listOf("abc",123,4,2,"fanda",12.4).filterIsInstance<String>())
//    println(listOf("abc",123,4,2,"fanda",12.4).filterType<String>())
//    printContents(listOf("abc","ddd"))
//    addAnswer(listOf("abc","ddd"))  // 编译不通过

//    enumerateCats(Animal::getIndex)

    testStar()
}

fun testGenericType() {

//    val authors = listOf("fanda", "liuhang")
    // <String> 是可以省略的
//    val authors = listOf<String>("fanda", "liuhang")

    // 在创建列表的函数中说明类型实参
//    val authors = listOf<String>()

    // 在变量声明中说明类型实参
//    val authors2: List<String> = listOf()

}

// 泛型扩展属性
//val <T> List<T>.penultimate: T
//    get() = this[size - 2]


/*interface List<T> {  // 定义类型参数 T
    operator fun get(index: Int): T     // T当作普通类型返回
}*/

/*class StringList : List<String> {   // 只有基类传入了具体类型
    override fun get(index: Int): String = ""
}*/

/*class ArrayList<T> : List<T> {  // 两个类后面都需要 <> 来定义类型形参

    override fun get(index: Int): T {
        //TODO
    }
}*/

//fun <T : Number> List<T>.sum(): T {}

fun <T : Number> oneHalf(value: T): Double {    // 定义 Number 为上界
    return value.toDouble() //调用 Number 的方法
}

//约束 T 同时是 CharSequence 和 Appendable 的实现类
fun <T> multiGenericLimit(seq: T) where T : CharSequence, T : Appendable {
    if (!seq.endsWith(".")) {   //调用 CharSequence 的方法
        seq.append(".") ////调用 Appendable 的方法
    }
}

// 非空形参
class Processor<T : Any> {
    fun process(value: T) {
        // 需要安全调用
        value.hashCode()
    }
}

//默认是可空形参
class Processor2<T> {
    fun process(value: T) {
        // 需要安全调用
        value?.hashCode()
    }
}

fun testProcessor() {
    val p = Processor2<String?>()    //可以传入可空类型
    print(p.process(null))  //可以传入 null
}

fun testList() {
//    val value = listOf(1, 2, 3,"a")

    // 不能这样判断，类型实参会被擦除
//    if (value is List<Int>) { }

    // 是否是一个列表
//    if (value is List<*>){}
}

//fun printSum(c: Collection<*>) {
//    val intList = c as? List<Int> ?: throw IllegalArgumentException("List is expected")
//    println(intList.sum())
//}

fun testPrintSum() {
    // 6
//    printSum(listOf(1,2,3))
    // java.lang.IllegalArgumentException: List is expected
//    printSum(setOf(1,2,3))
    // java.lang.ClassCastException: java.lang.String cannot be cast to java.lang.Number
//    printSum(listOf("a","b","c"))
}

// 不能通过编译
//fun <T> isA(value: Any) = value is T


// 实化类型参数后，可以通过编译
inline fun <reified T> isA(value: Any) = value is T

// 注意，这里是 List<*> 的扩展，表示任何未知类型的列表
inline fun <reified T> List<*>.filterType(): List<T> {
    val result = mutableListOf<T>()
    for (e in this) {
        if (e is T) {
            result.add(e)
        }
    }
    return result
}

fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

interface Producer<out T> {
    fun produce(): T
}

// ========================= 变型 ==========================//

//interface Function<in P, out R> {
//    operator fun invoke(p: P): R
//}

fun enumerateCats(f: (Cat) -> Number) {
}

fun Animal.getIndex() = 0

fun <T> copyData(source: MutableList<T>, des: MutableList<T>) {
    source.forEach {
        des.add(it)
    }
}

fun <T : R, R> copyData2(source: MutableList<T>, des: MutableList<R>) {
    source.forEach {
        des.add(it)
    }
}

fun <T> copyData3(source: MutableList<out T>, des: MutableList<T>) {
    source.forEach {
        des.add(it)
    }
}

fun <T> copyData4(source: MutableList<T>, des: MutableList<in T>) {
    source.forEach {
        des.add(it)
    }
}

fun <T> copyData5(source: List<T>, des: MutableList<T>) {
    source.forEach {
        des.add(it)
    }
}

fun testCopyData() {
//    copyData(mutableListOf(1,2,3), mutableListOf<Any>())    // 不能编译，要相同类型
    copyData2(mutableListOf(1, 2, 3), mutableListOf<Any>())    // 正常使用，但是用了两个形参
    copyData3(mutableListOf(1, 2, 3), mutableListOf<Any>())    // 正常使用，使用点变型 out
    copyData4(mutableListOf(1, 2, 3), mutableListOf<Any>())    // 正常使用，使用点变型 in
    copyData5(mutableListOf(1, 2, 3), mutableListOf<Any>())    // 正常使用，使用 List
}

fun testStar() {
    val list: MutableList<Any?> = mutableListOf("fanda", 1, "wrdf")
    val chars = mutableListOf('a', 'b', 'c')
    val unkownList: MutableList<*> = if (Random().nextBoolean()) list else chars
//    unkownList.add(42)    // 编译器禁止调用这个方法
    println(unkownList.first())     // 读取元素是安全的
}


















