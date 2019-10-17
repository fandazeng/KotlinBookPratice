package zeng.fanda.com.kotlinpratice.object;

import java.io.File;
import java.util.List;

/**
 * @author 曾凡达
 * @date 2019/10/15
 */
public class ObjectPraticeJava {

    private static InnerClass innerClass = new InnerClass();

    public static void main(String[] args) {
        // 可以访问嵌套类的 private 成员
        System.out.println(innerClass.age);

        System.out.println(FileComparator.INSTANCE.compare(new File("/User"), new File("/user")));

//        System.out.println( Dog.ObjectDog.getAge());
//        Dog.ObjectDog.run();

//        System.out.println( Dog.age);
//        Dog.run();

        final int clickCount = 0 ;
        new TestView().setListener(new Listener() {

            @Override
            public void onClick() {
                System.out.println(clickCount);
            }
        });

        new TestView().setLongClickListener(new LongClickListener() {
            @Override
            public void onLongClick() {

            }
        });

    }

    static class InnerClass {
        private int age = 18;
    }

    interface Listener{
        void onClick();
    }

    interface LongClickListener{
        void onLongClick();
    }

    static class TestView{
        private Listener listener;
        private LongClickListener longClickListener;

        public void setListener(Listener listener) {
            this.listener = listener;
        }

        public void setLongClickListener(LongClickListener longClickListener) {
            this.longClickListener = longClickListener;
        }
    }
}
