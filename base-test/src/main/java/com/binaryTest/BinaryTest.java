package com.binaryTest;

/**
 * Created by wangxiaodi1 on 2019/2/25.
 */
public class BinaryTest {
    private static final int MAXIMUM_CAPACITY = 1<<30;

    public static void displacement(){
        //<< 左移
        System.out.println("1<<1--->"+( 1 << 30));
        System.out.println("1<<1--->"+(( 1 << 30)>>>1));
        System.out.println("255<<1--->"+(255<<1));
        System.out.println("255<<<1--->"+(255>>>1));
        System.out.println("255>>1--->"+(255>>1));
    }

    private static final int tableSizeFor(int c) {
        int n = c - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    static void checkBounds(int off, int len, int size) { // package-private
        System.out.println( (off + len) <0);
        System.out.println( (off | len | (off + len)));
        if ((off | len | (off + len) | (size - (off + len))) < 0)
            throw new IndexOutOfBoundsException();
    }

    public static void main(String[] args) {
        checkBounds(-327690,327690,32767);
    }

}
