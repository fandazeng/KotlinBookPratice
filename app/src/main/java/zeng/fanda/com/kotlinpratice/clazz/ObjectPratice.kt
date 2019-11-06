package zeng.fanda.com.kotlinpratice.clazz

import android.content.Context
import java.io.File

/**
 *
 * @author 曾凡达
 * @date 2019/10/14
 *
 */

fun main() {
//    Button().showOff()

//    val userSuper = UserSuper()
//    println(userSuper.nickName)
//    val doctor = Doctor("fanda")
//    doctor.address = "xilichaguang"
//    testClient()

//    testObject()

    testCompanion()

}

// 定义一个接口
interface Clickable {
    fun click()

    // 接口带默认实现
    fun showOff() = println("I am a clickable!")
}

// 接口实现类
class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")

    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

}

interface Focusable {
    // 接口带默认实现
    fun showOff() = println("I am a Focusable!")
}

open class RichButton : Clickable {  // 这个类是 open 的，可以被其他类继承

    fun disable() {}     //这个函数是 final 的，不能被子类重写

    open fun animate() {}    //这个函数是 open 的，可以被子类重写

    override fun click() {} //这个函数重写了一个 open 函数并且本身也是 open 的

}

// 抽象类，默认为 open
abstract class Animated {

    abstract fun animate()      // 抽象方法，默认为 open

    fun animateTwice() {}       // 非抽象方法，默认为 final

    open fun stopAnimating() {}  // 非抽象方法，显式指定是 open

}

internal open class TalkativeButton : Focusable {

    private fun yell() = println("Hey!")

    protected fun whisper() = println("Let is talk !")
}

/*fun TalkativeButton.giveSpeech() {   // 这里报错，需要加上 internal
    yell()     // 这里报错，无法调用 private 方法

    whisper()   // 这里报错，无法调用 protected 方法
}*/


/*class ObjectPratice {

    private val innerClass: MyInnerClass = MyInnerClass()

    fun main() {
        // 不能访问嵌套类的 private 成员，会报错
        println(innerClass.age)
    }

    class MyInnerClass {
        private val age = 18
    }
}*/

class OuterClass {

    // 这是嵌套类，不持有外部类引用
    class NestedClass {

    }

    // 这是内部类，持有外部类引用
    inner class innerClass { //声明  inner
        fun getOuter() = this@OuterClass
    }

}

// 一个密封基类
sealed class Expr

// 简单的值对象，实现 Expr 接口
class Num(val value: Int) : Expr()

// Sum 运算，需要左右两个简单值对象，运用多态，声明成基类
class Sum(val left: Expr, val right: Expr) : Expr()

// 如果表达式是 Num 类型，直接返回值，如果是 Sum 类型，先计算左右表达式的值再求和
fun eval(e: Expr): Int =
    when (e) {
        // 用 is 判断实例，不需要再强转
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
//        else -> throw Exception("error")  // 不需要了，已经覆盖所有的情况
    }


//==================================== 构造方法 ====================================//

// 最简构造
//class User(val nickName: String)

// 最明确
/*class User constructor(nickName: String, age: Int) {
    val nickName: String
    val age: Int

    // 初始化语句块
    init {
        this.nickName = nickName
    }

    init {
        this.age = 18
    }
}*/


/*// 省略 constructor，属性与初始化语句结合
class User(_nickName: String, _age: Int) {
    val nickName = _nickName
    val age = _age
}*/

// 构造方法有默认值
//open class UserSuper(val nickName: String = "fanda", val age: Int = 18)

// 有父类，需要把参数传递给父类来初始化
//class User(val myName: String, nickName: String, age: Int) : UserSuper(nickName, age)

//class Son(var grade: Int)

//interface Run
//open class Car
//class Bus private constructor(): Car(), Run

open class View {
    constructor()
    constructor(ctx: Context)
    constructor(ctx: Context, attrs: String)

    fun onClickListener() {

    }
}

class MyView : View {

    val mContext: Context
    val mAttrs: String

    //    constructor(ctx: Context):super(ctx)
    constructor(ctx: Context) : this(ctx, "default")    // 从构造方法

    // super 用于父类的初始化
    constructor(ctx: Context, attrs: String) : super(ctx, attrs) {// 从构造方法
        mContext = ctx
        mAttrs = attrs
    }

}

open class OtherView(count: Int) {
    //    constructor(ctx: Context) : this()// 从构造方法
    constructor(ctx: Context) : this(ctx, "default")// 从构造方法

    constructor(ctx: Context, attrs: String) : this(count = 10)      // 从构造方法
}


//==================================== 实现在接口中声明的属性 ====================================//

interface IUser {
    // 声明一个抽象属性
    val nickName: String
}

// 直接用最简语法，实现接口抽象属性，需要加上 override
class privateUser(override val nickName: String) : IUser

class CallUser(val email: String) : IUser {
    override val nickName: String
        get() = email.substringBefore("@")  //只有 getter 方法，不是字段
}

class BookUser(val accountId: Int) : IUser {
    override val nickName = getBookName(accountId)

    fun getBookName(accountId: Int) = "$accountId@name" //有字段支持

}

interface ITeacher {
    val email: String   //这是抽象属性，需要重写
    val nickName: String    // 这个属性可以被继承，但是没有字段支持，每次会调用 getter 方法
        get() = email.substringBefore("@")
}

class Doctor(val name: String) {
    var address: String = "default"
        set(value) {
            println(
                """
            Address was changed for $name: "$field" -> "$value".
        """.trimIndent()
            )
            field = value
        }
}

class LengthCounter {
    var counter: Int = 0
        private set     // 变成私有的，不能在类外部修改这个属性

    fun addWord(word: String) {
        counter += word.length
    }
}


//==================================== 数据类和类委托 ====================================//

class Client(val name: String, val postalCode: Int) {
    override fun toString(): String {
        return "Client(name=$name,postalCode=$postalCode)"
    }

    override fun equals(other: Any?): Boolean {
        val c = other as? Client ?: return false
        return name == c.name && postalCode == c.postalCode
    }

/*    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) {
            return false
        }
        // 因为上面判断了类型，所以这里编译器会对 other 进行智能转换为 Client
        return name == other.name && postalCode == other.postalCode
    }*/

    override fun hashCode(): Int {
        return name.hashCode() * 31 + postalCode
    }

    fun copy(name: String = this.name, postalCode: Int = this.postalCode) = Client(name, postalCode)
}

data class NewClient(val name: String, val postalCode: Int)

data class OtherClient(val name: String) {
    var postalCode: Int = 0

    constructor(_postalCode: Int) : this("") {
        this.postalCode = _postalCode
    }
}

// 测试数据类方法
fun testClient() {
//    val client = Client("fanda", 123456)
//    val client2 = Client("fanda", 123456)

//    val client = NewClient("fanda", 123456)
//    val client2 = NewClient("fanda", 123456)

//    val client = OtherClient("fanda")
//    client.postalCode = 123456
//    val client2 = OtherClient("fanda")
//    client2.postalCode = 123456
//
//    val hashSet = hashSetOf(client)
//
//    println(client.toString())
//    println(client == client2)
//    println(hashSet.contains(client2))
//    println(client.copy("liuhang").name)
//    println(client.copy("liuhang").postalCode)

}


// 装饰类
/*class DelegationCollection<T> : Collection<T> { // 实现与原始类同样的接口
    // 原始类保存为字段
    private val innerList = arrayListOf<T>()

    // 分别转发原始类对应的方法
    override val size = innerList.size
    override fun contains(element: T) = innerList.contains(element)
    override fun containsAll(elements: Collection<T>) = innerList.containsAll(elements)
    override fun isEmpty() = innerList.isEmpty()
    override fun iterator() = innerList.iterator()
}*/

class DelegationCollection<T>(private val innerList: Collection<T> = ArrayList()) : Collection<T> by innerList {
    var count = 0

    override fun isEmpty(): Boolean {
        count++
        return innerList.isEmpty()
    }
}


//==================================== 对象声明 ====================================//

class Person

// 对象声明，不需要 class ，将类声明与该类的单一实例声明结合到了一起
object Payroll {
    //    init {
//        println("init")
//    }
    val allEmplayees = arrayListOf<Person>()

    fun calculateSalary() {
        for (person in allEmplayees) {
            //...
        }
    }
}

fun testObject() {
//    Payroll.allEmplayees.add(Person())
//    Payroll.calculateSalary()

//    println(FileComparator.compare(File("/User"), File("/user")))

//    val files = listOf(File("/Z"), File("/a"))
//    println(files.sortedWith(FileComparator))

    val manList = listOf(Man("fanda"), Man("hehe"))
    println(manList.sortedWith(Man.NameComparator))
}

object FileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1.path.compareTo(o2.path, ignoreCase = true)
    }
}

data class Man(val name: String) {
    object NameComparator : Comparator<Man> {
        override fun compare(o1: Man, o2: Man): Int {
            return o1.name.compareTo(o2.name)
        }
    }
}

interface IEat {
    fun eat()
}

open class Base

// 伴生对象
class Dog {
    companion object : IEat, Base() {
        override fun eat() {
            println("I am eating")
        }

        @JvmField
        val age = 5

        @JvmStatic
        fun run() {
            println("I am running")
        }
    }
}

// 用来测试伴生对象
fun testCompanion() {
//    println(Dog.age)
//    println(Dog.run())
//    println(Dog.ObjectDog.run())

//    println(Cat.ObjectCat.age)
//    println(Cat.ObjectCat.run())

//    println(User.newCallUser("2543533434@qq.com"))
//    println(User.newBookUser(435730))

    C.func()
}

class Cat {
    object ObjectCat {
        val age = 5
        fun run() {
            println("I am running")
        }
    }
}


data class User private constructor(val nickName: String) {   // 有一个私有的构造方法的数据类

    companion object {
        // 工厂方法生成 User 对象
        fun newCallUser(email: String) = User(email.substringBefore("@"))

        fun newBookUser(accountId: Int) = User(getBookName(accountId))

        private fun getBookName(accountId: Int) = "$accountId@name" //有字段支持
    }
}

// 伴生对象的扩展
class C {
    companion object
}

fun C.Companion.func() {
    println("我是扩展函数")
}

fun testNoneNameInnerClass() {

    val listener = object : ObjectPraticeJava.Listener, ObjectPraticeJava.LongClickListener {
        override fun onClick() {
        }

        override fun onLongClick() {
        }
    }
    ObjectPraticeJava.TestView().setListener(listener)
    ObjectPraticeJava.TestView().setLongClickListener(listener)


    /*
    var clickCount = 0
    ObjectPraticeJava.TestView().setListener(object : ObjectPraticeJava.Listener {
        override fun onClick() {
            clickCount++
            println(clickCount)
        }
    })*/
}








