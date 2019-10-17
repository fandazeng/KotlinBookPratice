package zeng.fanda.com.kotlinpratice.base

/**
 * @author 曾凡达
 * @date 2019/9/26
 */
class Person {
    val name: String

    var isMarried: Boolean

    // 构造方法
    constructor(_name: String, _isMarried: Boolean) {
        name = _name
        isMarried = _isMarried
    }

}
