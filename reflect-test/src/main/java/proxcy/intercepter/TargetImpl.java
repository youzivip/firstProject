package proxcy.intercepter;

/**
 * Created by wangxiaodi1 on 2018/7/5.
 */

public class TargetImpl implements Target {
    @Override
    public void execute() {
        System.out.println("execute");
    }

    @Override
    public void execute1() {
        System.out.println("execute1");
    }
}
