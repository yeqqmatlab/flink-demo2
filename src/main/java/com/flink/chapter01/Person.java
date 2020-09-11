package com.flink.chapter01;

/**
 * created by yqq 2020/9/8
 */
public class Person {

    public String name;

    public Integer age;

    public Person(){

    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
