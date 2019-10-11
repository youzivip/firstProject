package com.ioc.configurationAnnoTest;

import com.ioc.domain.Actor;
import com.ioc.domain.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangxiaodi1 on 2019/3/6.
 */
@Configuration("configuration2")
public class Configuration2 {

    @Bean(name = "actorC")
    public Actor getActor(){
        Actor actor = new Actor();
        return actor;
    }
}
