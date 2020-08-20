package com.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;



/**
 * created by yqq 2020/8/20
 */
public  class LineSplitter implements FlatMapFunction<String, Tuple2<String,Integer>> {

    private static final long serialVersionUID = 1L;

    @Override
    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
        String[] split = s.split("\\W+");
        for (String word : split) {
            if (split.length > 0) {
                collector.collect(new Tuple2<String, Integer>(word,1));
            }
        }
    }
}
