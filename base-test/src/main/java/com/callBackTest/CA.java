package com.callBackTest;

public class CA {
    public static void main(String[] args) {
        CB cb = new CB();
        MyFuture<String> future = cb.callB();
        System.out.println(future.get());
    }
}
