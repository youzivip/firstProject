package streamTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangxiaodi1 on 2018/10/15.
 */
public class StreamTest {

    public static void collectTest() {
        List<Integer> list = Stream.of(1,2,5,3,32,12).collect(Collectors.toList());
        list.stream().filter(i -> {
            System.out.println(i);
            return i==0;
        }
        ).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void main(String[] args) {
        collectTest();
    }
}
