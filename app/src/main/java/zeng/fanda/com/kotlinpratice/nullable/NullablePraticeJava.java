package zeng.fanda.com.kotlinpratice.nullable;

/**
 * @author 曾凡达
 * @date 2019/10/29
 */
public class NullablePraticeJava {

    public static void main(String[] args) {
        new StringPrinter().process(null);
    }

    public int strLen(String s) {
        // 当 s 为 null 时，会报空指针
        return s.length();
    }
}
