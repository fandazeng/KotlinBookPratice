package zeng.fanda.com.kotlinpratice.clazz

/**
 *
 * @author 曾凡达
 * @date 2019/9/12
 *
 */

fun main() {
    // 生成序列
    val sequence = generateSequence(0) { it + 1 }.takeWhile { it <= 100 }
    print(sequence.sum())

}