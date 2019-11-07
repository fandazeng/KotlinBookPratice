@file:JvmName("FunctionUtil")

package zeng.fanda.com.kotlinpratice.function

import java.io.File.separator
import java.lang.IllegalArgumentException


/**
 *
 * @author 曾凡达
 * @date 2019/9/30
 *
 */

fun main() {
//    printClass(hasSet)
//    printClass(arrayList)
//    printClass(hasMap)
//    printLastOrMaxElement()

//    printDefaultToString()
//    println(joinToString(hasSet,"-","<",">"))
//    println(joinToString(hasSet,separator = "-", prefix = "<", postfix = ">"))
//    println(joinToString(hasSet))
//    println(joinToString(hasSet,";"))
    // 打乱了参数顺序，并且separator参数使用了默认值
//    println(joinToString(hasSet,postfix = "]",prefix = "["))

    // 扩展函数的调用
//    println(hasSet)
//    println(hasSet.joinToString("-", "<", ">"))
//    println(hasSet.joinToString(separator = "-", prefix = "<", postfix = ">"))
//    println(hasSet.joinToString(";"))
    // 打乱了参数顺序，并且separator参数使用了默认值
//    println(hasSet.joinToString(postfix = "]", prefix = "["))

    val list = listOf("fanda", "liuhang")
    println(list.joinToString())    //(fanda,liuhang)
    println(list.joinToString { it.toUpperCase() }) //(FANDA,LIUHANG)
    println(list.joinToString(separator = "!", transform = { it.toUpperCase() }))   //(FANDA!LIUHANG)

//    println("kotlin".lastChar())
//    print("kotlin".lastChar)
//    print(StringBuilder("kotlin").lastChar)


//    val view: View = Button()

    // 输出 Button clicked
//    view.click()

    // 输出 I am a View
//    view.showOff()

//    testVararg()
//    testInfix()
//    testRegrex()
}

//常见的集合类创建
val hasSet = hashSetOf(3, 6, 4)
val arrayList = arrayListOf(3, 6, 8)
val hasMap = hashMapOf(1 to "one", 2 to "two")

fun <T> printClass(collection: Collection<T>) {
    println(collection.javaClass)
}

// 调用集合的扩展函数
fun printLastOrMaxElement() {
    val list = arrayListOf("cat", "dog", "panda")
    val set = setOf(44, 77, 99, 11)
    println(list.last())    //panda
    println(set.max())      //99
}

fun printDefaultToString() {
    val set = setOf(44, 77, 99, 11)
    println(set)    // 默认触发 toString
}

/*@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ",",
    prefix: String = "(",
    postfix: String = ")"
): String {
    val builder = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) builder.append(separator)
        builder.append(element)
    }
    builder.append(postfix)
    return builder.toString()
}*/

// 顶层不变引用属性
val son = "son"
// 顶层可变引用属性
var father = "father"
// 顶层常量属性
const val TAG = "TAG"


//fun String.lastChar() = this.get(this.length - 1)

// 省略 this
fun String.lastChar() = get(length - 1)


/*// 终极版本，用扩展函数的方式实现
@JvmOverloads
fun <T> Collection<T>.joinToString(
    separator: String = ",",
    prefix: String = "(",
    postfix: String = ")"
): String {
    val builder = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) builder.append(separator)
        // 使用 toString 方法将对象转换为字符串
        builder.append(element)
    }
    builder.append(postfix)
    return builder.toString()
}*/

// 终极版本，用扩展函数的方式实现并带有 lambda 参数
/*@JvmOverloads
fun <T> Collection<T>.joinToString(
    separator: String = ",",
    prefix: String = "(",
    postfix: String = ")",
    transform: (T) -> String = { it.toString() }    // 默认实现
): String {
    val builder = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) builder.append(separator)
        // 使用函数类型的参数
        builder.append(transform(element))
    }
    builder.append(postfix)
    return builder.toString()
}*/

// 终极版本，用扩展函数的方式实现并带有 lambda 参数，但带 null 默认值
@JvmOverloads
fun <T> Collection<T>.joinToString(
    separator: String = ",",
    prefix: String = "(",
    postfix: String = ")",
    transform: ((T) -> String)? = null   // 声明一个函数类型可空的参数
): String {
    val builder = StringBuilder(prefix)
    for ((index, element) in withIndex()) {
        if (index > 0) builder.append(separator)
        // 安全调用，Elvis 运算符
        var result = transform?.invoke(element) ?: element.toString()
        builder.append(result)
    }
    builder.append(postfix)
    return builder.toString()
}


//==================================== 不可重写的扩展函数 ====================================//

// kotlin 中类和函数默认是 final 的，如果需要继承需要 open  修饰符
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {     //继承
    override fun click() {
        println("Button clicked")
    }
}

fun View.showOff() = println("I am a View")
fun Button.showOff() = print("I am a button")


//==================================== 扩展属性 ====================================//

val String.lastChar: Char
    get() = get(length - 1)

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) = setCharAt(length - 1, value)

//==================================== 可变参数和中缀调用 ====================================//

fun testVararg() {
    val array = arrayOf("kotlin", "java", "python")

    //输出 [php, [Ljava.lang.String;@5b2133b1]
    println(listOf("php", array))

    //输出 [php, kotlin, java, python]
    println(listOf("php", *array))
}

fun testInfix() {
//    val map = mapOf(1 to "one", 2 to "second")
    // 输出 {1=one, 2=second}
//    println(map)

//    1.to("one") // 普通函数调用
//    1 to "one"  // 中缀调用

    val (num, value) = 1 to "one"
    // 输出 num:1 , value:one
    println("num:$num , value:$value")

}

infix fun Any.to(other: Any) = Pair(this, other)


//==================================== 字符串和正则表达式 ====================================//

fun testRegrex() {
    // 用点号或破折号来分割字符串
    // 输出 [12, 345, 6, A]
    println("12.345-6.A".split("[.-]".toRegex()))
    println("12.345-6.A".split("\\.|-".toRegex()))

    // 可以指定多个分隔符
    println("12.345-6.A".split(".", "-"))
}

fun parsePath(path: String) {
    val directory = path.substringBeforeLast("/")
    val fullName = path.substringAfterLast("/")
    val fileName = fullName.substringBeforeLast(".")
    val extension = fullName.substringAfterLast(".")

    // 输出 Dir: /Users/yole/Kotlin-book , FullName: chapter.adoc , FileName: chapter , Extension: adoc
    println("Dir: $directory , FullName: $fullName , FileName: $fileName , Extension: $extension")
}

// 用正则表达式的方式来解析
fun parsePathRegex(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        val (directory, fileName, extension) = matchResult.destructured
        println("Dir: $directory , FileName: $fileName , Extension: $extension")
    }
}

val path = "/Users/yole/Kotlin-book/chapter.adoc"

val kotlinLogo = """.| //
    .|//
    .|/  \
""".trimMargin(".")


//==================================== 局部函数和扩展 ====================================//

// 用户类
class User(val id: Int, val name: String, val address: String)

/*// 保存用户数据
fun saveUser(user: User) {

    // 数据校验
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }

    println("Save user success!")
}*/

// 利用局部函数的方式
fun saveUser(user: User) {

    // 定义局部函数，可以直接访问外部函数所有的参数和变量
    fun validate(value: String, fileName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $user.id: empty $fileName")
        }
    }

    validate(user.name, "Name")
    validate(user.address, "Address")

    println("Save user success!")
}

// 将校验逻辑封装到类的扩展函数里
fun User.validateBeforeSave() {
    // 定义局部函数，可以直接访问外部函数所有的参数和变量
    fun validate(value: String, fileName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fileName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

// 利用扩展函数来优化
fun saveUserLocal(user: User) {
    user.validateBeforeSave()
    println("Save user success!")
}



















