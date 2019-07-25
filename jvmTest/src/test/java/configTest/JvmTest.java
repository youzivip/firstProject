import java.util.Objects;

/**
 * 参数配置测试GC
 * Created by wangxiaodi1 on 2019/1/24.
 */
public class JvmTest {
    public static void main(String[] args) {
        Byte[] b = null;//yaoyongnull
        for (int i=0;i<10;i++){
            b = new Byte[1024*1024];
        }
    }
}
