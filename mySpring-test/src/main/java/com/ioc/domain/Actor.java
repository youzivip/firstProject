package com.ioc.javaBase.domain;

/**
 * Created by wangxiaodi1 on 2019/3/4.
 */
public class Actor {
    private String name;
    private int sex;
    private int age;

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
