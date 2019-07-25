/**
 * Created by wangxiaodi1 on 2018/12/17.
 */
public class Parent {
    public String name = "p";

    public String getName() {
        System.out.println("p.name:" + name);
        return name;
    }

    public String getNick() {
        System.out.println(name + "=" + getName());
        return name + "Nick";
    }

    public void print() {
        System.out.println("======parent:" + name);
    }
}
