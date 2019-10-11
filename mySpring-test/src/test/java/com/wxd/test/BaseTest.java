package com.wxd.test;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lixiuhai on 2016/9/23.
 */
@ContextConfiguration("classpath:spring-config.xml")
@RunWith(SpringJUnit4ClassRunner.class)
//    @Configuration
//    @ImportResource("classpath:spring-config.xml")
//    @RunWith(SpringJUnit4ClassRunner.class)
public class BaseTest {

}
