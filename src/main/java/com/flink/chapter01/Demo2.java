package com.flink.chapter01;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * created by yqq 2020/9/10
 */
public class Demo2 {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.fromElements(1, 2, 3, 4, 5).map(i -> i*i).print();

        env.fromElements(1,2,3,4,5).map(i -> Tuple2.of(i,i)).returns(Types.TUPLE(Types.INT,Types.INT)).print();

        env.execute("demo2");
    }
}
