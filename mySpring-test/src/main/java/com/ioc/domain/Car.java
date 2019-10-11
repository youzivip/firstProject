package com.ioc.domain;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;

/**
 * Created by wangxiaodi1 on 2019/3/4.
 */
public class Car implements BeanFactoryAware{

    private BeanFactory factory;

    private String brand;
    private String color;
    private int maxSpeed;

    public Car() {
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("set BeanFactory************");
        this.factory = beanFactory;
    }

    public BeanFactory getFactory() {
        return factory;
    }

    public Car(String brand, String color, int maxSpeed) {
        this.brand = brand;
        this.color = color;
        this.maxSpeed = maxSpeed;
    }

    //未带参的方法
    public void introduce(){
        System.out.println("brand:"+brand+",color:"+color+",maxSpeed:"+maxSpeed);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
