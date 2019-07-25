package iteratorTest;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by wangxiaodi1 on 2018/11/29.
 */
public class Test {
    private static Set<Artist> allArtist = new HashSet<>();
    static{
        allArtist.add(new Artist("xianghai"));
        allArtist.add(new Artist("London"));
    }

    public static void main(String[] args) {
       // iOld();
       //  lazy();   //如果返回的是stream 就是惰性求值，如果返回的是空或者是另一个值，那么就是及早求值
       // testCollect();
       // testMap();   //map中写function 进行操作，没有返回值的  map中要是想做if判断，抛弃一些值怎么做呢 // TODO: 2018/11/29
       // testFilter();   //过滤，返回的是boolean，这个一般和哪些及早求值一起用呀// TODO: 2018/11/29
       // testFlapmap();  //里边又用stream,但是没有看太明白
        testMaxAndMin();  //使用的comparing 可以研究下


    }

    private static void testMaxAndMin() {
        List<Artist> allArtist = Arrays.asList(new Artist("china"),new Artist("America"),new Artist("England"));
        Artist minA = allArtist.stream().min(Comparator.comparing(artist -> artist.getPlace())).get();
        Artist maxA = allArtist.stream().max(Comparator.comparing(artist -> artist.getPlace())).get();
        System.out.println("min-->"+minA);
        System.out.println("max-->"+maxA);

        // comparing 接受一个函数，并返回另一个函数
    }

    private static void testFlapmap() {
        List<Integer> to = Stream.of(Arrays.asList(1,3),Arrays.asList(3,4))
                .flatMap(num->num.stream()).collect(Collectors.toList());
        to.stream().forEach(i-> System.out.print(i+"   "));

    }

    private static void testFilter() {
       Long c =  allArtist.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println("c-->"+c);
        allArtist.stream().filter(artist -> {
            System.out.print(artist.getPlace()+"    ");
            return artist.isFrom("London");
        }).count();

        //不能这样做转换呢，返回什么都没有 // TODO: 2018/11/29
       List<Artist> artists =  allArtist.stream().filter(artist ->
               artist.isFrom("London")
         ).collect(Collectors.toList());
        artists.stream().filter(artist -> {
            System.out.print(artist.getPlace()+"    ");
            return artist.isFrom("London");
        }).count();
    }

    private static void testMap() {
        List<String> l = Stream.of("a","b","c").collect(Collectors.toList());
        List<String> lu = new ArrayList<>();
        for (String s:l){
            String up = s.toUpperCase();
            lu.add(up);
        }
        System.out.println(Stream.of("A","B","C").collect(Collectors.toList()).equals(lu));

        //使用map转换成另一种类型
        List<String> l1 = Stream.of("a","b","c").map( strings -> strings.toUpperCase()).collect(Collectors.toList());
        List<String> l2 = l.stream().map(s -> s.toUpperCase()).collect(Collectors.toList());
        System.out.println(Stream.of("A","B","C").collect(Collectors.toList()).equals(l1));
        System.out.println(Stream.of("A","B","C").collect(Collectors.toList()).equals(l));   //false 需要有另一个list来接收
        System.out.println(Stream.of("A","B","C").collect(Collectors.toList()).equals(l2));

    }

    public static void testCollect(){
        List<String> collected = Stream.of("a","b","c").collect(Collectors.toList());
        System.out.println(collected.equals(Arrays.asList("a","b")));
    }

    public static void iOld(){
        int count = 0;
        //原有的
        Iterator<Artist> iterator = allArtist.iterator();
        while (iterator.hasNext()){
            Artist artist = iterator.next();
            if(artist.isFrom("London")){
                count ++ ;
            }
        }
        System.out.println(count);

        //jdk 8的
        //Stream 是用函数式编程方式在集合类上进行复杂操作的工具
        //filter 只是刻画了Stream，最终不产生新集合的方法  惰性求值方法
        //count  最终会从Stream中产生值的方法交及早求值方法
        Long c = allArtist.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println(c);


    }

    public static void lazy(){
        //由于惰性求值，不会打印出
        allArtist.stream().filter(artist -> {
            System.out.println(artist.getPlace());
            return artist.isFrom("London");
        });

        //加上及早求值就可以了
        Long c = allArtist.stream().filter(artist -> {
            System.out.println(artist.getPlace());
            //  return artist.isFrom("London");
            return true;
        }).count();
        System.out.println(c);

    }


}
