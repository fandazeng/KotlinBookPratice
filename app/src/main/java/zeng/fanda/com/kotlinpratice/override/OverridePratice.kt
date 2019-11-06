package zeng.fanda.com.kotlinpratice.override

import android.annotation.SuppressLint
import java.beans.PropertyChangeListener
import java.beans.PropertyChangeSupport
import java.math.BigDecimal
import java.time.LocalDate
import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 *
 * @author 曾凡达
 * @date 2019/11/4
 *
 */

fun main() {
//    testPoint()
//    testCircle()
//    testMutalbePoint()
//    testAssignCollection()
//    testInc()
//    testPerson()
//    testRectangle()
//    testRangeTo()
//    testRangeTo2()
//    testRangeTo3()
//    testIterator()

//    test()
//    testNameComponents()
//    testPrintEntries2()
//    testMan()
    testPropertyChange()
}

//=================================重载一元二元运算符=========================//

// 不可变属性
data class Point(val x: Int, val y: Int) {

    // 重载加号运算
//    operator fun plus(other: Point) = Point(x + other.x, y + other.y)

    override fun equals(other: Any?): Boolean {
//        val p = other as? Point ?: return false
//        return p.x == x && p.y == y

        if (other === this) return true
        if (other !is Point) return false
        return other.x == x && other.y == y

    }
}

data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

// 可变属性
data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.get(index: Int) = when (index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

operator fun MutablePoint.set(index: Int, value: Int) = when (index) {
    0 -> x = value
    1 -> y = value
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

operator fun Point.get(index: Int) = when (index) {
    0 -> x
    1 -> y
    else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
}

operator fun Point.plus(other: Point) = Point(x + other.x, y + other.y)
operator fun Point.unaryMinus() = Point(-x, -y)
operator fun MutablePoint.plusAssign(other: MutablePoint) {
    x += other.x
    y += other.y
}

operator fun Point.times(scale: Double) = Point((x * scale).toInt(), (y * scale).toInt())
operator fun Double.times(p: Point) = Point((this * p.x).toInt(), (this * p.y).toInt())
operator fun Char.times(count: Int) = toString().repeat(count)

operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    object : Iterator<LocalDate> {
        var current = start

        override fun hasNext() = current <= endInclusive

        @SuppressLint("NewApi")
        override fun next(): LocalDate = current.apply { current = plusDays(1) }
    }

operator fun Circle.minus(other: Circle) = reduce(other)

fun testPoint() {
    val p1 = Point(10, 50)
    val p2 = Point(100, 3)
    // Point(x=110, y=53)
    println(p1 + p2)
    // Point(x=30, y=150)
    println(p1 * 3.0)
    println(3.0 * p1)
    // aaa
    println('a' * 3)
    //Point(x=-10, y=-50)
    println(-p1)

    println(p1[0])
}

fun testAssignCollection() {
//    val numbers = ArrayList<Int>()
//    numbers += 42
    // [42]
//    println(numbers)

    val list = arrayListOf(1, 2)
    list += 3   // 添加单个元素
    println(list)

    var list2 = listOf(100, 200)   // 这里要用 var 声明可变的
    list2 += 5   // 因为作用于只读变量，会返回一个修改过的副本，即引用被改变了
    println(list2)

    val newList = list + listOf(4, 5)   // 添加类型一致的其他集合
    println(newList)
}

fun testMutalbePoint() {
    val p1 = MutablePoint(10, 50)
    p1 += MutablePoint(100, 3)
    //MutablePoint(x=110, y=53)
    println(p1)
    println(p1[0])
    p1[1] = 100
    println(p1)

}

fun testCircle() {
    val c1 = Circle(15, 15)
    val c2 = Circle(5, 5)

    println(c1 + c2)    //Circle{x=20, y=20}
    println(c1 - c2)    //Circle{x=10, y=10}
}

operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun testInc() {
    var bd = BigDecimal.ZERO
    // 0
    println(bd++)
    // 2
    println(++bd)
}

fun testRectangle() {
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    println(Point(20, 20) in rect)   //true
    println(Point(5, 5) in rect)     //false
}

@SuppressLint("NewApi")
fun testRangeTo() {
    val now = LocalDate.now()
    val vacation = now..now.plusDays(10)
    println(now.plusWeeks(1) in vacation)   //true
}

fun testRangeTo2() {
    val n = 9
    println(0..n + 1)   //0..10
    println(0..(n + 1))   //最好这样写，用括号括起来    0..10

    (0..n).forEach { print(it) }
}

@SuppressLint("NewApi")
fun testRangeTo3() {
    val newYear = LocalDate.ofYearDay(2017, 1)
    val daysOff = newYear.minusDays(1)..newYear
    for (dayOff in daysOff) {
        println(dayOff)
    }
}

fun testIterator() {
    for (s in "adfsfsdf") {
        print(s)
    }
}


//=================================重载比较运算符=========================//

class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    override fun compareTo(other: Person): Int {
        // 按顺序调用给定的方法，并比较它们的值
        return compareValuesBy(this, other, Person::lastName, Person::firstName)
    }
}

fun testPerson() {
    val p1 = Person("fanda", "zeng")
    val p2 = Person("hang", "liu")

    // false
    println(p1 < p2)
}

//=================================解构声明=========================//

fun test() {
    val p = OtherPoint(50, 30)
    val (x, y) = p
    println(x)
    println(y)
}

class OtherPoint(val x: Int, val y: Int) {
    operator fun component1() = x
    operator fun component2() = y
}

data class NameComponents(val name: String, val ext: String)

fun splitFileName(fullName: String): NameComponents {
    // 集合上也有定义约定的 componentN 函数，所以这里可以用解构声明
    val (name, ext) = fullName.split(".", limit = 2)
    return NameComponents(name, ext)
}

fun splitFilename(fullName: String): Pair<String, String> {
    // 集合上也有定义约定的 componentN 函数，所以这里可以用解构声明
    val (name, ext) = fullName.split(".", limit = 2)
    return Pair(name, ext)
}

fun testNameComponents() {
    val (name, ext) = splitFilename("fanda.exe")
    println(name)   //fanda
    println(ext)    //exe
}

// 解构声明在 for 循环的运用
fun testPrintEntries() {
    val map = mapOf("one" to "first", "two" to "second")
    for ((key, value) in map) {
        println("key is $key , value is $value")
    }
}

// 原理
fun testPrintEntries2() {
    val map = mapOf("one" to "first", "two" to "second")
    for (entry in map.entries) {
        val key = entry.component1()
        val value = entry.component2()
        println("key is $key , value is $value")
    }
}

//=================================委托属性=========================//

class Email {/*...*/ }

fun loadEmail(person: Man): List<Email> {
    println("Load emails for ${person.name}")
    return listOf(/*...*/)
}

class Man(val name: String) {
/*    private var _emails: List<Email>? = null
    val emails: List<Email>
        get() {
            if (_emails == null) {
                _emails = loadEmail(this)
            }
            return _emails!!    // 非空断言
        }*/

    val emails by lazy { loadEmail(this) }

}

fun testMan() {
    val m = Man("fanda")
    m.emails    // 第一次访问会打印 Load emails for fanda
    m.emails    // 这里不再打印，因为属性已经有值
}

open class PropertyChangeAware {
    protected val changeSupport = PropertyChangeSupport(this)

    fun addPropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.addPropertyChangeListener(listener)
    }

    fun removePropertyChangeListener(listener: PropertyChangeListener) {
        changeSupport.removePropertyChangeListener(listener)
    }
}

/*class Teacher(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int = age
        set(value) {
            val oldValue = field
            field = value
            // 属性变化时通知监听器
            changeSupport.firePropertyChange("age", oldValue, value)
        }

    var salary: Int = salary
        set(value) {
            val oldValue = field
            field = value
            // 属性变化时通知监听器
            changeSupport.firePropertyChange("salary", oldValue, value)
        }
}*/

/*class Teacher(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    // 委托对象
    var _age = ObservableProperty("age", age, changeSupport)
    var age: Int
        set(value) = _age.setValue(value)
        get() = _age.getValue()

    // 委托对象
    var _salary = ObservableProperty("salary", salary, changeSupport)
    var salary: Int
        set(value) = _salary.setValue(value)
        get() = _salary.getValue()
}*/

/*class ObservableProperty(val propName: String, var propValue: Int, val changeSupport: PropertyChangeSupport) {
    fun getValue() = propValue
    fun setValue(value: Int) {
        val oldValue = propValue
        propValue = value
        changeSupport.firePropertyChange(propName, oldValue, value)
    }
}*/

/*class ObservableProperty(var propValue: Int, val changeSupport: PropertyChangeSupport) {
    // operator 标记
    operator fun getValue(t: Teacher, prop: KProperty<*>) = propValue

    // operator 标记
    operator fun setValue(t: Teacher, prop: KProperty<*>, value: Int) {
        val oldValue = propValue
        propValue = value
        changeSupport.firePropertyChange(prop.name, oldValue, value)
    }
}*/

/*class Teacher(val name: String, age: Int, salary: Int) : PropertyChangeAware() {
    var age: Int by   ObservableProperty(age, changeSupport)
    var salary: Int by ObservableProperty(salary, changeSupport)
}*/

class Teacher(val name: String, age: Int, salary: Int) : PropertyChangeAware() {

    // 声明一个 lambda ，告诉如何通知属性值的更改
    private val observer = { prop: KProperty<*>, oldValue: Int, newValue: Int ->
        changeSupport.firePropertyChange(prop.name, oldValue, newValue)
    }

    var age: Int by Delegates.observable(age, observer)
    var salary: Int by Delegates.observable(salary, observer)
}


fun testPropertyChange() {
    val t = Teacher("fanda", 25, 2000)
    // SAM 构造
    t.addPropertyChangeListener(PropertyChangeListener { event ->
        println("Property ${event.propertyName} changed from ${event.oldValue} to ${event.newValue}")
    })

    t.age = 26
    t.salary = 10000
}






