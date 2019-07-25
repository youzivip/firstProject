package aa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangxiaodi1 on 2018/8/6.
 */
@RestController
@SpringBootApplication
public class AppTest {
    @RequestMapping("/HelloWord")
    public String sayHello(){
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(AppTest.class,args);
    }

}
