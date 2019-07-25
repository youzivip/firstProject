/**
 * Created by wangxiaodi1 on 2018/12/17.
 */
public class Son extends Parent {
    public String name = "s";


    @Override
    public String getName() {
        return name;
    }

    public void print(){
        System.out.println("======son:" + name);
    }
}
