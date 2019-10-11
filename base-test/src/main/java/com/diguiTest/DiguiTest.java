package com.diguiTest;

public class DiguiTest {
    public static int digui(int i,int sum){
        if(i == 0) return sum;
        else{
            sum += i;
            i--;
            return digui(i,sum);
        }
    }

    public static void main(String[] args) {

        System.out.println(digui(10,0));
    }
}
