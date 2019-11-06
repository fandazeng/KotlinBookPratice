package zeng.fanda.com.kotlinpratice.override;


/**
 * @author 曾凡达
 * @date 2019/11/4
 */
public class OverridePraticeJava {

    public static void main(String[] args) {
        testPlusPoint();
    }

    public static void testPlusPoint() {
        Point p1 = new Point(100, 50);
        Point p2 = new Point(10, 10);
        System.out.println(OverridePraticeKt.plus(p1,p2));
    }
}
