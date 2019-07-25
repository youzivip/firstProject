package simple;

import com.lmax.disruptor.EventHandler;

/**
 * Created by wangxiaodi1 on 2018/10/9.
 */
public class LongEventHandler implements EventHandler<LongEvent>{

    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        System.out.println("event-->"+event+",curThread:"+Thread.currentThread().getName());

    }

}
