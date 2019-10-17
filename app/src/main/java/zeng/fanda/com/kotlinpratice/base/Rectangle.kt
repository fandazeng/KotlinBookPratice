package zeng.fanda.com.kotlinpratice.base

/**
 *
 * @author 曾凡达
 * @date 2019/9/26
 *
 */
class Rectangle(val height: Int, val width: Int) {

    val isSquare: Boolean
        get() = height == width
}