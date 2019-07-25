/**
 * Created by wangxiaodi1 on 2018/12/17.
 */
public class ExtendsTest {
    public static void main(String[] args) {
        Parent son = new Son();
        System.out.println(son.name+"====="+son.getName());
        System.out.println("=====================" + son.getNick());
    }
}
