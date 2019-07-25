/**
 * Created by wangxiaodi1 on 2018/12/6.
 */
public class CompareTest {
    public static void main(String[] args) {
        Integer a = 10;
        Integer b = 12;
        Integer c = 11;
        Integer d = 10;
        System.out.println(c.compareTo(a));   //c>a 1
        System.out.println(c.compareTo(b));   //c<b -1
        System.out.println(d.compareTo(a));   // == 0
    }
}
