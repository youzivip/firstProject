package com.ioc.configurationAnnoTest;

import com.ioc.domain.Actor;
import com.ioc.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangxiaodi1 on 2019/3/6.
 */
@Configuration
public class Configuration1 {

    @Autowired
    Configuration2 configuration2;

    @Bean(name = "carC")
    public Car getCar(){
        Car car = new Car("BenZ","black",1);
        doSth();
        return car;
    }

    public void doSth(){
        Actor actor = configuration2.getActor();
        actor.setName("saca");
        System.out.println(actor);
    }

}
