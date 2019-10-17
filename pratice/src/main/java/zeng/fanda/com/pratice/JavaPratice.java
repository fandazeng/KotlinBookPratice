package zeng.fanda.com.pratice;


import zeng.fanda.com.kotlinpratice.strings.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 曾凡达
 * @date 2019/8/30
 */
public class JavaPratice {

    public void main(String[] args) {
        List<Integer> list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        StringUtils.joinToString(list, ",", "<", ">");
        // 默认以这种形式暴露给 JAVA 使用
//        StringUtils.getCONST();
        System.out.println(StringUtils.FINAL_CONST);
    }
}
