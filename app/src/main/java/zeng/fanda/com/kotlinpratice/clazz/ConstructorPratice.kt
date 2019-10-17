package zeng.fanda.com.kotlinpratice.clazz

import android.content.Context

/**
 *
 * @author 曾凡达
 * @date 2019/9/5
 *
 */

// 以下三种声明的方式都是等价的，最后一种最简洁

class User constructor(_nickname: String) {
    val nickname: String

    init {
        nickname = _nickname
    }
}

// 省略 constructor
class User2(_nickname: String) {
    val nickname = _nickname
}

// 将 val 放在参数前面，替换类中的属性定义 ，这种方式最简洁
class User3(val nickname: String)

// 给构造方法声明默认值

class User4(val nickname: String, val isSubecribed: Boolean = true)

// =============================

// 有继承关系的类的构造方法初始化

open class Parent(val nickname: String)

// 这里的构造没有 val ，不是 son 类的属性，主要是参数声明，用来给父类初始化的
class Son(nickname: String, val age: Int = 18) : Parent(nickname)

// 私有构造方法
class Personal private constructor()

// 显式声明多个从构造方法

open class View {
    constructor(ctx: Context)

    constructor(ctx: Context, attr: String)
}

class MyButton : View {
    // 需要通过 super 来初始化父类的构造
    constructor(ctx: Context) : super(ctx)

    constructor(ctx: Context, attr: String) : super(ctx, attr)

}

// 可以通过 this 调用另一个构造方法

class MyTextView : View {

    // 通过 this 来调用另一个构造
    constructor(ctx: Context) : this(ctx, "attr")

    // 这个构造仍然要初始化父类的构造
    constructor(ctx: Context, attr: String) : super(ctx, attr)
}

class Dudu {
    val name: String

    constructor(name: String) {
        this.name = name
    }
}

class LengthCounter{
    //不能把属性设置为private，不然外部访问不了
     var counter: Int = 0
         // 把 setter 设置为 private，即不能让外部修改该属性
        private set

    fun addWord(word: String) {
        counter+=word.length
    }

}

// 实现接口中声明的属性

interface IUser {
    val nickname: String
}

interface ITeacher{
    val email : String
    // 接口也可以有 getter
    val nickname: String
        get() =  email.substringBefore('@')
}

// 直接通过主构造方法声明了接口的抽象属性
class MainUser(override val nickname: String) : IUser

// 通过 getter 的方式提供接口的抽象属性的值
class EmailUser(val email: String) : IUser {

    override val nickname: String
        get() = email.substringBefore("@")
}

// 通过 setter 的方式初始化接口的抽象属性的值
 class SetterUser(val accountId: String) : IUser {

    override val nickname = getName()

    fun getName() = accountId + "@name"
}

fun main() {
//    val fanda = User4("Fanda")
//    println(fanda.isSubecribed)
//
//    val liuhang = User4("liuhang", false)
//    println(liuhang.isSubecribed)
//
//    val other = User4(nickname = "other", isSubecribed = true)
//    println(other.isSubecribed)
//
//    val son = Son("hehe")
//    println(son.nickname + son.age)

//    val mainUser = MainUser("fanda")
//    val setterUser = SetterUser("fanda")
//    println(mainUser.nickname)
//    println(setterUser.nickname)

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Fanda")
    println(lengthCounter.counter)
}
