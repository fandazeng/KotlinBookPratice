package zeng.fanda.com.kotlinpratice.clazz

/**
 *
 * @author 曾凡达
 * @date 2019/9/4
 *
 */

interface Clickable {
    // 普通的方法声明
    fun click()

    // 带默认实现的方法
    fun showOff() = println("I'm clickable!")
}

interface Focusable {

    // 带默认实现的方法
    fun setFocus(focus: Boolean) = println("I ${if (focus) "got" else "lose"} focus !")

    // 带默认实现的方法
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    // 此时必须显式实现
    override fun showOff() {
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }

    override fun click() {
        println("I was clicked")
    }

}

open class RichButton : Clickable {

    // 默认是 final 的
    fun disable() {

    }

    // 用 open 修饰后的方法可以被覆写
    open fun animate() {

    }

    // 本身是 open 的，如果要阻止子类重写，需要加上 final
    final override fun click() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

class OtherButton : RichButton() {
    override fun animate() {
        super.animate()
    }
}

// 抽象类
abstract class Animated {
    // 定义抽象方法
    abstract fun animate()

    // 非抽象类，默认是 final 的
    fun stopAnimating() {

    }

    // 用 open 表示可以被重写
    open fun startAnimate() {

    }
}

// prvate 修饰，文件内可见
private val test = ""

fun test() {
    println(test)
}

class Test {
    fun method() {
        print(test)
    }
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")

    protected fun whisper() = println("Let's talk!")
}

// 扩展函数的可见性不能大于其本身的可见性，这里不能为 Public
internal fun TalkativeButton.giveSpeech() {

    // 该方法调用不了，扩展函数会不能调用 private 的属性和方法
//    yell()

    // 该方法调用不了，扩展函数会不能调用 protected 的属性和方法
//    whisper()

}


fun main() {
//    val button = Button()
//    button.showOff()
//    button.setFocus(false)
//    button.click()

    val age = 18
    val name = "Fanda"
    println("我叫$name,我今年${age}岁")
}
