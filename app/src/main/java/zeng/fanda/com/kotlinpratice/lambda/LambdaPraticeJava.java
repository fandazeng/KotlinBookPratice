package zeng.fanda.com.kotlinpratice.lambda;

import android.content.Context;
import android.content.ContextWrapper;
import android.widget.Button;

/**
 * @author 曾凡达
 * @date 2019/10/21
 */
public class LambdaPraticeJava {

    public static void main(String[] args) {
    }

    public static void postponeCoputation(int delay, Runnable computation) {
        computation.run();
    }

}
