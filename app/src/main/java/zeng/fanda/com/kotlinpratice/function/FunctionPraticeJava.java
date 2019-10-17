package zeng.fanda.com.kotlinpratice.function;

import kotlin.collections.CollectionsKt;

import java.util.ArrayList;

/**
 * @author 曾凡达
 * @date 2019/9/30
 */
public class FunctionPraticeJava {

    public static void main(String[] args) {
//        printString();
//        System.out.println(FunctionUtil.lastChar("kotlin"));
        testVarage();
    }

    public static void printString() {
        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(FunctionUtil.joinToString(list, ";"));

    }

    public static void testVarage() {
        String[] array = new String[]{"kotlin", "java", "python"};
        // 输出 [kotlin, java, python]
        System.out.println(CollectionsKt.listOf(array));
        // [php, [Ljava.lang.String;@1f32e575]
        System.out.println(CollectionsKt.listOf("php",array));
    }

}
