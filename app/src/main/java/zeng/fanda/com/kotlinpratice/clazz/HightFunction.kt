package zeng.fanda.com.kotlinpratice.clazz

/**
 *
 * @author 曾凡达
 * @date 2019/9/12
 *
 */

// 高阶函数，传入一个函数类型的参数
fun twoAndThree(operation: (Int, Int) -> Int) {
    val result = operation(2, 3)
    println("The result is $result")
}

// 基于 String 类型的 filter 函数，是一个扩展函数
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until length) {
        val element = get(index)
        if (predicate(element)) {
            sb.append(element)
        }
    }
    return sb.toString()
}

fun main() {
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }
    println("abc123g".filter { it in 'a'..'z' })
}