package com.flink.chapter01;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * created by yqq 2020/9/10
 */
public class Splitter implements FlatMapFunction<String, Tuple2<String,Integer>> {

    @Override
    public void flatMap(String line, Collector<Tuple2<String, Integer>> out) throws Exception {
        for (String word : line.split(" ")) {
            out.collect(new Tuple2<>(word,1));
        }
    }
}
