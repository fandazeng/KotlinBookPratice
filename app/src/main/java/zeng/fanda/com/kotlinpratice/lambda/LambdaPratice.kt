package zeng.fanda.com.kotlinpratice.lambda

import android.arch.lifecycle.Transformations.map
import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.TextView
import zeng.fanda.com.kotlinpratice.R
import java.io.File
import java.lang.StringBuilder

/**
 *
 * @author 曾凡达
 * @date 2019/10/21
 *
 */

fun main() {
//    val personList = listOf(Person("fanda", 18), Person("liuhang", 13))
    // 用普通函数实现
//    findTheOldest(personList)
    // 用库函数实现
//    println(personList.maxBy({ it.age }))

//    println(personList.maxBy(Person::age))

//    personList.maxBy({ p: Person -> p.age })
//    personList.maxBy() { p: Person -> p.age }
//    personList.maxBy { p: Person -> p.age }
//    personList.maxBy { p -> p.age }
//    personList.maxBy {  it.age }

//                println (lambda(13, 15))
//    println(sum(43,1))

//    val errors = listOf("403 Forbidden", "404 Not Found")
//    printMessageWithPrefix(errors, "Error:")
//    printProblemCounts(errors)

//    println(getAge(Person("fanda",18)))

//    run(::salute)

//    printPerson()

//    testFilter()
//    testCondition()
//    testGroupBy()
//    testFlatMapAndFlatten()
//    testSequence()
//    testSequence2()
//    testGenerateSequence()

//    val file = File("/Users/svtk/.HiddenDir/a.txt")
//    println(file.isInsideHiddenDirectory())

//    testLabdaMethod2()

//    createRunnable().run()

    println(testNoWithMethod())
    println(testWithMehtod())
}


data class Person(val name: String, val age: Int)

// 找到年龄最大的人
fun findTheOldest(list: List<Person>) {
    var maxAge = 0
    var theOldestPerson: Person? = null     // ？ 表示可以为null

    for (p in list) {
        if (p.age > maxAge) {
            maxAge = p.age
            theOldestPerson = p
        }
    }
    println(theOldestPerson)

}

// 参数      //分隔符  // 函数体
val lambda = { x: Int, y: Int -> x + y }

val sum = { x: Int, y: Int ->
    println("Computing the sum of $x and $y")
    x + y
}

fun printMessageWithPrefix(messages: Collection<String>, prefix: String) {
    // 接收 lambda 作为实参指定对每个元素的操作
    messages.forEach {
        // 在 lambda 中访问 "prefix" 参数
        println("$prefix $it")
    }
}

fun printProblemCounts(response: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0

    response.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}

// =========================== 成员引用 ======================//

// 类    //成员
val getAge = Person::age

fun salute() = println("salute")

fun sendEmail(person: Person, message: String) {
}

// 有两个参数的 lambda
val action = { person: Person, message: String ->
    // 委托给 sendEmail 函数
    sendEmail(person, message)
}

val nextAction = ::sendEmail

fun printPerson() {
    // 创建 Person 实例的动作被保存成了值
    val createPerson = ::Person
    // 延期执行创建类实例
    val p = createPerson("fanda", 19)
    println(p)

//    val predicate = Person::isAdult
//    println(predicate(p))

    val predicate = p::isAdult
    println(predicate())
}

// 扩展函数
fun Person.isAdult() = age >= 18


// =========================== 集合的函数式 API ======================//

fun testFilter() {
//    val list = listOf(1, 2, 3, 4)
//    println(list.filter { it % 2 == 0 })
//    println(list.map { it * it })

    val personList = listOf(Person("fanda", 38), Person("liuhang", 22))
    // 输出  [Person(name=fanda, age=38)]
    println(personList.filter { it.age > 30 })
    // 输出  [fanda, liuhang]
//    println(personList.map { it.name })
    // 成员引用的写法
//    println(personList.map(Person::name))

    // [fanda]
//    println(personList.filter { it.age > 30 }.map { it.name })

//    val maxAge = personList.maxBy(Person::age)!!.age
//    println(personList.filter { it.age == personList.maxBy(Person::age)!!.age })
//    println(personList.filter { it.age == maxAge})

  /*  val numbers = mapOf(0 to "zero", 1 to "one")
    // 变换值  {0=ZERO, 1=ONE}
    println(numbers.mapValues { it.value.toUpperCase() })
    // 过滤值  {0=zero}
    println(numbers.filterValues { it.length > 3 })

    // 变换键  {1=zero, 2=one}
    println(numbers.mapKeys { it.key + 1 })

    // 过滤键  {1=one}
    println(numbers.filterKeys { it > 0 })*/
}


fun testCondition() {
    val canBeInClub27 = { p: Person -> p.age <= 27 }

    val personList = listOf(Person("fanda", 38), Person("liuhang", 22))

    println(personList.all(canBeInClub27))  // false
    println(personList.any(canBeInClub27))  // true

    println(personList.count(canBeInClub27))  // 1  ,该方法更高效
    println(personList.filter(canBeInClub27).size)  //1     ，该方法有中间集合

    println(personList.find(canBeInClub27))  // Person(name=liuhang, age=22)
    println(personList.firstOrNull(canBeInClub27))  // Person(name=liuhang, age=22)

    // true
    println(!personList.all { it.age == 22 })  // ! 否定不明显，最好用 any 替换
    // true
    println(personList.any { it.age != 22 })  // lambda 参数中的条件要取反

}

fun testGroupBy() {
//    val personList = listOf(Person("fanda", 38), Person("liuhang", 22), Person("dudu", 22))
    // {38=[Person(name=fanda, age=38)], 22=[Person(name=liuhang, age=22), Person(name=dudu, age=22)]}
//    println(personList.groupBy { it.age })
//    println(personList.groupBy { it.age }.mapValues {
//        it.value.joinToString { p: Person -> p.name }
//    })

    val list = listOf("a", "ab", "dad", "rs")
    // {a=[a, ab], d=[dad], r=[rs]}
    println(list.groupBy(String::first))
}

data class Book(val title: String, val authors: List<String>)

fun testFlatMapAndFlatten() {

    val books = listOf(
        Book("a", listOf("aaa1", "aaa2")),
        Book("b", listOf("bbb1", "bbb2")),
        Book("c", listOf("ccc1", "ccc2")),
        Book("d", listOf("aaa1", "aaa2"))
    )

    // 只做了变换操作，[[aaa1, aaa2], [bbb1, bbb2], [ccc1, ccc2], [aaa1, aaa2]]
    println(books.map { it.authors })
    // 做了变换且平铺 [aaa1, aaa2, bbb1, bbb2, ccc1, ccc2, aaa1, aaa2]
    println(books.flatMap { it.authors })
    // 平铺且去重 [aaa1, aaa2, bbb1, bbb2, ccc1, ccc2]
    println(books.flatMap { it.authors }.toSet())

    val list = listOf(listOf("aaa", "bbb", "ccc"), listOf("111", "222"), listOf(1, 2, 3))
    // [aaa, bbb, ccc, 111, 222, 1, 2, 3]
    println(list.flatten())
}

fun testSequence() {
    val personList = listOf(Person("fanda", 38), Person("liuhang", 22), Person("dudu", 22))
    val newList = personList.map(Person::name).filter { it.startsWith("f") }
    println(newList)

    val sequenceList = personList.asSequence()       // 把初始集合转换成序列
        .map(Person::name).filter { it.startsWith("f") }
        .toList()   // 把结果序列转换回列表
    println(sequenceList)
}

fun testSequence2() {
    // 没有默认操作，不会输出任何内容
    /*  listOf(1, 2, 3, 4).asSequence().map {
          // 写成多行的
          print("map($it) ")
          it * it
      }.filter {
          print("filter($it) ")
          it % 2 == 0
      }*/

    listOf(1, 2, 3, 4).asSequence()
        // 写在一行，要有分号 ；
        .map { print("map($it) "); it * it }
        .filter {
            print("filter($it) "); it % 2 == 0
        }.toList()  // 末端操作
}

fun testGenerateSequence() {
    val numbers = generateSequence(0) { it + 1 }  // 生成一个序列
    val numbersTo100 = numbers.takeWhile { it <= 100 }  // 中间操作，生成另一个序列
    println(numbersTo100.sum()) // 末端操作，执行所有延期操作，找到结果
}

fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }.any { it.isHidden }

val runnable = object : Runnable {
    override fun run() {
        println(this.hashCode())
    }
}

fun testLambdaMethod() {
//    LambdaPraticeJava.postponeCoputation(1000) { println(42) }

    LambdaPraticeJava.postponeCoputation(1000, object : Runnable {
        override fun run() {
            println(this.hashCode())
        }
    })
}

fun testLabdaMethod2() {
    for (count in 1..3) {
        testLambdaMethod()
    }

    println()

    for (count in 1..3) {
        LambdaPraticeJava.postponeCoputation(1000, runnable)
    }
}

// 返回一个 SAM 构造方法生成的对象
fun createRunnable() = Runnable { println("Create Success") }

val listener = View.OnClickListener { view ->
    val text = when (view.id) {
        R.id.button_1 -> "First Button"
        R.id.button_2 -> "Second Button"
        else -> "Unknown Button"
    }
    println(text)
}

fun testListener(context: Context) {
    val firstButton = Button(context)
    val secondButton = Button(context)

    firstButton.setOnClickListener(listener)
    secondButton.setOnClickListener(listener)

}

fun testNoWithMethod(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nEnd")
    return result.toString()
}

fun testWithMehtod() =
    with(StringBuilder()) {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nEnd")
        toString()
    }

fun testApplyMethod() =
    StringBuilder().apply {
        for (letter in 'A'..'Z') {
            append(letter)
        }
        append("\nEnd")
    }.toString()

fun testBuildString() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nEnd")
}

fun createViewWithCustomAttr(context: Context) =
    TextView(context).apply {
        text = "simple text"
        textSize = 19f
        setPadding(0, 10, 0, 10)
    }







