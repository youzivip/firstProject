package com.classTest;

/**
 * Created by wangxiaodi1 on 2019/3/12.
 */
public class ClassTest {
    public static void main(String[] args) {
        System.out.println(A.class.isAssignableFrom(A.class));
        System.out.println("Aa extends A and A is Assignable Aa："+A.class.isAssignableFrom(Aa.class));
        System.out.println("Aa extends A and Aa is Assignable A："+Aa.class.isAssignableFrom(Aa.class));
        System.out.println(A.class.isAssignableFrom(B.class));
     }
}
