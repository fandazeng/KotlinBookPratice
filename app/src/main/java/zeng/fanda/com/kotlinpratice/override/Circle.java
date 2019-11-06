package zeng.fanda.com.kotlinpratice.override;

/**
 * @author 曾凡达
 * @date 2019/11/4
 */
public class Circle {
    private int x;
    private int y;

    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // 函数名和参数数量都匹配
    public Circle plus(Circle other) {
        return new Circle(x + other.x, y + other.y);
    }

    // 函数名不匹配
    public Circle reduce(Circle other) {
        return new Circle(x - other.x, y - other.y);
    }

    @Override
    public String toString() {
        return "Circle{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
