package zeng.fanda.com.kotlinpratice.dsl

import java.lang.StringBuilder

/**
 *
 * @author 曾凡达
 * @date 2019/11/19
 *
 */

fun main() {
/*
    val content =buildString {
        it.append("test")
        it.append("haha")
    }*/

/*    val content = buildString {
        append("test")
        append("haha")
    }

    val appendExc: StringBuilder.() -> Unit = { append("fdsf") }
    println(buildString(appendExc))
    println(content)*/

//    println(createTable())

    val test = Test()

    // 单个处理
    test.compile("4324343443443@lmdlfmdf")

    // 批量处理
    test {
        compile("432434343243@gfdvdg")
        compile("fs@gfdvdg")
        compile("fsff@gfdvdg")
    }

}

/*fun buildString(builderAction: (StringBuilder) -> Unit): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}*/


/*// 带接收者的 lambda
fun buildString(builderAction: StringBuilder.() -> Unit): String {
    val sb = StringBuilder()
    builderAction(sb)
    return sb.toString()
}*/

// 带接收者的 lambda
fun buildString(builderAction: StringBuilder.() -> Unit): String = StringBuilder().apply(builderAction).toString()

open class Tag(val name: String) {
    private val children = mutableListOf<Tag>()

    protected fun <T : Tag> doInit(child: T, init: T.() -> Unit) {
        child.init()
        children.add(child)
    }

    override fun toString(): String {
        return "<$name>${children.joinToString("")}</$name>"
    }
}

fun table(init: TABLE.() -> Unit) = TABLE().apply(init)

class TABLE : Tag("table") {
    fun tr(init: TR.() -> Unit) = doInit(TR(), init)

}

class TR : Tag("tr") {
    fun td(init: TD.() -> Unit) = doInit(TD(), init)
}

class TD : Tag("td")

fun createTable() = table {
    this@table.tr {
        this@tr.td {
        }
    }
}


class Test {

    fun compile(content: String) {
        println(content)
    }

    operator fun invoke(content: Test.() -> Unit) {
        content()
    }
}

