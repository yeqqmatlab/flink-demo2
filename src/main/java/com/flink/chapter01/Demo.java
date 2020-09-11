package com.flink.chapter01;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import java.util.ArrayList;
import java.util.List;

/**
 * created by yqq 2020/9/8
 */
public class Demo {
    public static void main(String[] args) throws Exception {

      final  StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Person> ds = env.fromElements(new Person("jack", 30), new Person("tom", 20),new Person("tom2", 16));

        List<Person> list = new ArrayList<>();
        list.add(new Person("tom", 20));
        list.add(new Person("tom2", 17));
        list.add(new Person("tom3", 30));

        DataStream<Person> ds2 = env.fromCollection(list);

        DataStream<Person> filterDS = ds2.filter((FilterFunction<Person>) person -> person.age > 18);

        filterDS.print();

        env.execute("demo");

    }
}

