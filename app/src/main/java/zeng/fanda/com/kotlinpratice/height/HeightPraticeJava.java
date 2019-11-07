package zeng.fanda.com.kotlinpratice.height;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 曾凡达
 * @date 2019/11/6
 */
public class HeightPraticeJava {

    public static void main(String[] args) {

/*        HeightPraticeKt.processTheAnswer(number -> number + 1);
        // 单抽象接口 Function1
        HeightPraticeKt.processTheAnswer(new Function1<Integer, Integer>() {
            @Override
            public Integer invoke(Integer integer) {
                return integer + 1;
            }
        });*/
        callKotlinMethod();
    }

    public static void callKotlinMethod() {
        List<String> strings = new ArrayList<>();
        strings.add("42");
        // Java 8 之前
        CollectionsKt.forEach(strings, new Function1<String, Unit>() {
            @Override
            public Unit invoke(String s) {
                System.out.println(s);
                return Unit.INSTANCE;
            }
        });

        // Java 8 之后，用 lambda 的方式
        CollectionsKt.forEach(strings, s -> {
            System.out.println(s);
            return Unit.INSTANCE;
        });
    }

    /**
     *  try-with-resource 的模式使用
     */
     public static String readFirstLineFormFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

}
