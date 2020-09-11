package com.flink.chapter01;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.shaded.curator.org.apache.curator.shaded.com.google.common.base.Joiner;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * created by yqq 2020/9/9
 */
public class SocketText {

    private static final Joiner ADD_JOINER = Joiner.on(":");


    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<String> socketDS = env.socketTextStream("ip248", 9999, "\n");

        DataStream<Tuple2<String, Integer>> wordsDS = socketDS
                .map((MapFunction<String, Tuple2<String, Integer>>) value -> new Tuple2<>(value, 1))
                .returns(Types.TUPLE(Types.STRING,Types.INT))
                .keyBy(0)
                .sum(1);

        /*DataStream<String> msgDS = socketDS.map(new MapFunction<String, String>() {
            @Override
            public String map(String value) throws Exception {
                String socketMsg = ADD_JOINER.join("socket msg", value);
                return socketMsg;
            }
        });*/

        wordsDS.print();

        env.execute("socket");
    }
}
