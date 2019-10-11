package leetCode.other.math;

/**
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class CiFang {
    public static double Power(double base, int exponent) {
        if(exponent == 0) return 1;
        else  if (exponent>0) return  Power( base,  exponent-1) * base;
        return 1/Power(base,0-exponent);
    }

    public static void main(String[] args) {
        System.out.println(Power(3.45,5));
        System.out.println(Power(3.45,-5));
    }
}
