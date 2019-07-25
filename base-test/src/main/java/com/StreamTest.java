import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangxiaodi1 on 2018/12/6.
 */
public class StreamTest {
    public static void main(String[] args) {
        Test test = new Test();
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        test.setA(list);


        list = test.getA().stream().filter(a->!a.equals("c")).collect(Collectors.toList());
        test.setA(list);
        test.getA().forEach(a-> System.out.println(a));
    }

    public static class Test{
        List<String> a;

        public List<String> getA() {
            return a;
        }

        public void setA(List<String> a) {
            this.a = a;
        }
    }
}
