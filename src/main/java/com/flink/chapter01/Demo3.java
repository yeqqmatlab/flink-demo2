package com.flink.chapter01;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;

/**
 * created by yqq 2020/9/10
 */
public class Demo3 {
    public static void main(String[] args) throws Exception {

        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<Person> csv = env.readCsvFile("data/csv/person.csv").fieldDelimiter(",").pojoType(Person.class, "name", "age");



        csv.print();

    }
}
