package zeng.fanda.com.kotlinpratice.base

import kotlin.random.Random
import zeng.fanda.com.kotlinpratice.base.Color.*
import java.io.BufferedReader
import java.lang.NumberFormatException
import java.util.*


/**
 *
 * @author 曾凡达
 * @date 2019/9/26
 *
 */

// 经典的打印 Hello,world 方法
fun main(args: Array<String>) {
//    println("Hello,world!")
//    printString()
//    printPerson()
//    printRectangle()
//    println(createRandomRectangle().width)

//    println(BLUE.name + " == " + Color.BLUE.ordinal)
//    playGame()
//    printCharBinary()
//    printList()
    throwExpection(-1)

}

// 带返回值的函数示例
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

// 表达式体的方式展示
fun maxExpression(a: Int, b: Int) = if (a > b) a else b

// 赋值操作是语句，没有值，不能直接返回，该函数编译失败
/*
fun assignment(a: Int): Int {
    return  a = 100
}*/

//==================================== 变量 ====================================//

// 显式指定变量类型
val question: String = "Are you Ok ?"
// 通过类型推导
val answer = "yes"

class Variable {
    // 需要显式指定类型
    val otherAnswer: String

    constructor(cotent: String) {
        // 在构造方法中赋值
        otherAnswer = if (cotent.isEmpty()) "no" else "yes"
    }
}

fun testVariable() {
    // val 引用的对象可能是可变的
    val languages = arrayListOf("kotlin", "java")
    languages.add("python")

//    var answer = "yes"
//    answer = 100
}

// 顶层属性不能直接操作语句
//val languages = arrayListOf("kotlin", "java")
//languages.add("python")


// 字符串模板功能

fun printString() {
    val content = "test"
    val age = 18
    println("打印的内容如下： $content")
    println("年纪为：${age}岁")

    println("转义：\$$content")

    println("1 + 2 = ${1 + 2}")

    println("age${if (age > 10) "大于10" else "小于10"}")
}

fun printPerson() {
    val person = Person("fanda", true)
    println(person.name)
    println(person.isMarried)
}

fun printRectangle() {
    val rectangle = Rectangle(100, 100)
    println(rectangle.isSquare)
}

// 创建矩形的顶层函数
fun createRandomRectangle() = Rectangle(Random.nextInt(), Random.nextInt())


//==================================== 枚举和 when ====================================//

//enum class Color {
//    RED, GREEN, BLUE
//}

// 声明带属性和方法的枚举类
enum class Color(private val r: Int, private val g: Int, private val b: Int) {
    RED(255, 0, 0), GREEN(0, 255, 0), BLUE(0, 0, 255);// 这里必须要有分号，用于分隔枚举常量和方法

    // 给枚举类定义一个方法
    fun rgb() = (r * 256 + g) * 256 + b

}

// 直接返回一个 when 表达式的表达式体函数
fun getColorStr(color: Color) =
    when (color) {
        RED -> "red"
        GREEN -> "green"
        BLUE -> "blue"
        else -> "nothing"
    }

// 导入常量来简化代码
fun getColor(color: Color) = when (color) {
    BLUE, GREEN -> "bule and green"
    RED -> "red"
}

// 以 set 对象为判断条件(实际上 When 允许使用任何对象做分支条件)
// 每次调用该函数都会创建 set 对象，效率较低，如果该函数调用非常频繁，都生成好多垃圾对象
fun mix(c1: Color, c2: Color) =
    when (setOf(c1, c2)) {
        setOf(RED, GREEN) -> BLUE
        setOf(RED, BLUE) -> GREEN
        else -> throw Exception("nothing")
    }

// 省略 when 后面的参数，每一个分支条件都是一个布尔表达式
fun mixOptimized(c1: Color, c2: Color) =
    when {
        (c1 == RED && c2 == GREEN) || (c1 == GREEN && c2 == RED) -> BLUE
        (c1 == RED && c2 == BLUE) || (c1 == BLUE && c2 == RED) -> GREEN
        else -> throw Exception("nothing")
    }


//==================================== 智能转换 ====================================//

// 一个基类接口
interface Expr

// 简单的值对象，实现 Expr 接口
class Num(val value: Int) : Expr

// Sum 运算，需要左右两个简单值对象，运用多态，声明成基类
class Sum(val left: Expr, val right: Expr) : Expr

// 如果表达式是 Num 类型，直接返回值，如果是 Sum 类型，先计算左右表达式的值再求和
fun eval(e: Expr): Int =
    when (e) {
        // 用 is 判断实例，不需要再强转
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw Exception("error")
    }

// 分支逻辑用代码块的形式
fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            // 最后一行表达式就是结果
            e.value
        }
        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            left + right
        }
        else -> throw IllegalArgumentException("Unkonw expression")
    }


//==================================== 迭代和区间 ====================================//

val oneToTen = 1..10

fun game(value: Int) =
    when {
        value % 15 == 0 -> "FizzBuzz "
        value % 5 == 0 -> "Buzz "
        value % 3 == 0 -> "Fizz "
        else -> "$value "
    }

fun playGame() {
    for (value in 1..50 step 2) {
        println(game(value))
    }
}

fun playGameDown() {
    for (value in 50 downTo 0 step 2) {
        println(game(value))
    }
}

fun printCharBinary() {
    // 使用 TreeMap 让键来排序
    val binaryReps = TreeMap<Char, String>()
    // 使用字符区间从 A 到 F 之间的字符
    for (c in 'A'..'F') {
        // 把 ASCII 码转换成二进制
        val binary = Integer.toBinaryString(c.toInt())
        // 根据键把值存储到 map 中 ，等价于 binaryReps.put(c,binary)
        binaryReps[c] = binary
    }

    // 迭代 map ，把键和值赋给两个变量
    for ((letter, binary) in binaryReps) {
        println("$letter == $binary")
    }
}

// 打印带索引下标的集合
fun printList() {
    val list = arrayListOf("a", "b", "c")
    for ((index, element) in list.withIndex()) {
        println("$index == $element")
    }
}

// 判断字符是否是字母
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

// 判断字符是否不是数字
fun isNotDigit(c: Char) = c !in '0'..'9'

// 用 when 的方式来处理
fun recognize(c: Char) = when (c) {
    in 'a'..'z', in 'A'..'Z' -> "It is a letter!"
    in '0'..'9' -> "It is a digit!"
    else -> "I don't know !"
}


//==================================== 异常 ====================================//

fun throwExpection(value: Int) =
    if (value > 0) value else throw IllegalArgumentException("value == $value")

fun readNumber(reader: BufferedReader): Int? =
    try {
        val line = reader.readLine()
        Integer.parseInt(line)
    } catch (e: NumberFormatException) { // 异常类型在右边
        null
    } finally {
        reader.close()
    }


























