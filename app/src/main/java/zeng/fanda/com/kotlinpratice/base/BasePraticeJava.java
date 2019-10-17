package zeng.fanda.com.kotlinpratice.base;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/**
 * @author 曾凡达
 * @date 2019/9/26
 */
public class BasePraticeJava {

    public static void main(String[] args) {
//        System.out.println("Hello,world!");
        System.out.println(assignment(43));

    }

    public static int assignment(int a) {
        return a = 100;
    }

    public static void testPerson() {
        Person person = new Person("fanda", true);
        System.out.println(person.getName());
        System.out.println(person.isMarried());
    }

    public static String getColorStr(Color color) {
        String colorStr;
        switch (color) {
            case RED:
                colorStr = "red";
                break;
            case GREEN:
                colorStr = "green";
                break;
            case BLUE:
                colorStr = "blue";
                break;
            default:
                colorStr = "nothing";
                break;
        }
        return colorStr;
    }

    public static final int eval(Expr e) throws Exception {
        int result;
        // 用 instanceof 判断实例
        if (e instanceof Num) {
            result = ((Num) e).getValue();
        } else {
            if (!(e instanceof Sum)) {
                throw new Exception("error");
            }
            // 需要强制转换
            result = eval(((Sum) e).getLeft()) + eval(((Sum) e).getRight());
        }

        return result;
    }

}
