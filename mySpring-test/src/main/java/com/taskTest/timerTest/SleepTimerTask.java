package com.taskTest.timerTest;

import java.util.Date;
import java.util.TimerTask;

/**
 * Created by wangxiaodi1 on 2019/3/22.
 */
public class SimpleTimerTask extends TimerTask {
    int i = 0;
    @Override
    public void run() {
        System.out.println("第"+(i+1)+"次执行，执行时间："+new Date());
        i++;
        if(i>=10) cancel();
    }
}
