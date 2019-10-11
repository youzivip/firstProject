package com.aopTest.anonationTest;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import org.springframework.stereotype.Service;

/**
 * Created by wangxiaodi1 on 2019/3/11.
 */
@Service
public class AnnationUseService {
    @NeedTest(value = true)
    public void a(){
        System.out.println("AnnationUseService a");
    }

    @NeedTest(false)
    public void b(){
        System.out.println("AnnationUseService b");
    }
}
