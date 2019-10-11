package com.ioc.domain;

/**
 * Created by wangxiaodi1 on 2019/3/6.
 */
public class A {
    Car car;

    public void useCar(){
        car.introduce();
    }

    public void doNoth(){
        System.out.println("I am a");
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
