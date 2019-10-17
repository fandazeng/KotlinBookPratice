package zeng.fanda.com.kotlinpratice.clazz

import java.io.File
import java.math.BigDecimal

/**
 *
 * @author 曾凡达
 * @date 2019/9/6
 *
 */

class Client(val name: String, val postcard: Int) {
    override fun toString(): String = "Client(name = $name, postcard = $postcard)"

    override fun equals(other: Any?): Boolean {
        val client = other as? Client ?: return false
        return name == client.name && postcard == client.postcard
    }

    override fun hashCode(): Int = name.hashCode() * 31 + postcard

    fun copy(name: String = this.name, postcard: Int = this.postcard) = Client(name, postcard)
}

// data 类默认会实现上述手写的 4 个方法，避免重复这种模板代码
data class Student(val name: String, val age: Int)

// 委托类
class DelegatingCollection<T>(val innerList: Collection<T> = ArrayList<T>()) : Collection<T> by innerList

class CoutingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectAdded = 0

    // 重写
    override fun add(element: T): Boolean {
        objectAdded++
        return innerSet.add(element)
    }

    // 重写
    override fun addAll(elements: Collection<T>): Boolean {
        objectAdded += elements.size
        return innerSet.addAll(elements)
    }

}

// 单例类
object Payroll {
    val allContent = arrayListOf<String>()

    fun getAllContent() {
        for (content in allContent) {

        }
    }

}

// 比较两个文件的比较器单例对象
object FileComparator : Comparator<File> {
    override fun compare(o1: File, o2: File): Int {
        return o1?.path.compareTo(o2.path, true)
    }
}

data class Person(val name: String) {
    // 在类中声明单例对象
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int = o1.name.compareTo(o2.name)
    }
}

// 伴生对象
class Teacher private constructor(val name: String) {

    fun test(){
        println("test")
    }
    companion object {
        fun newSubscribingUser(email: String) =
            Teacher(email.substringBefore("@"))

        fun newOtherUser(account: String) =
            Teacher(account + ": account")
    }
}

// 扩展伴生对象方法

class Doctor(val firstName: String, val lastName: String) {
    companion object {}
}

fun Doctor.Companion.getSomethings() = "somethings"

fun main() {
//    val client = Client("fanda", 123456)
//    val client2 = Client("fanda", 123456)
//    val hashSet = hashSetOf(client)
//
//    println(client.toString())
//    println(client == client2)
//    println(hashSet.contains(client2))
//    print(client.copy("liuhang"))

    //
//    val student = Student("fanda", 18)
//    val student2 = Student("fanda", 18)
//    val hashSet2 = hashSetOf(student)

//    println(student.toString())
//    println(student == student2)
//    println(hashSet2.contains(student2))
//    println(student.copy("liuhang"))

    // 直接把类当作一个实例对象来处理
//    println(Payroll.allContent.add("first"))
//    println(Payroll.getAllContent())

//    println(FileComparator.compare(File("/User"), File("/user")))
//    val files = listOf(File("/Z"), File("/a"))
//    println(files.sortedWith(FileComparator))

//    val persons = listOf(Person("Fanda"), Person("Liuhang"))
//    println(persons.sortedWith(Person.NameComparator))

//    val teacher = Teacher.newSubscribingUser("34234dfs@gmail.com")
//    println(teacher.name)

    // 调用扩展方法
//    println(Doctor.getSomethings())

//    val array = Array(10) { i -> ('a' + i).toString()}
//    val list = listOf("a", "b", "c")

//    val intlist = listOf(1, 2, 3)
//     转为基本数据类型数组
//    intlist.toIntArray()


    val t = Teacher.newOtherUser("fanda")
    val teacherFunction = Teacher::test

    val otherFunction = t::name
//    println(teacherFunction(t))
//    println(otherFunction())
    println(otherFunction.invoke())

}

