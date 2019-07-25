package jvmTest;

import java.util.ArrayList;
import java.util.List;

public class GenericTypes {
    public static String method(List<String> list){
        System.out.println("invoke String");
        return "";
    }

    public static int method1(List<Integer> list){
        System.out.println("invoke integer");
        return 0;
    }

    public static void main(String[] args) {
        method(new ArrayList<String>());
        method1(new ArrayList<Integer>());
    }
}
