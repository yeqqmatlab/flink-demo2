package com.flink.kafka2redis;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommand;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisCommandDescription;
import org.apache.flink.streaming.connectors.redis.common.mapper.RedisMapper;

/**
 * redis映射器
 * created by yqq 2020/8/20
 */
public class RedisExampleMapper implements RedisMapper<Tuple2<String,Integer>> {
    @Override
    public RedisCommandDescription getCommandDescription() {
        return new RedisCommandDescription(RedisCommand.HSET,"flink");
    }

    @Override
    public String getKeyFromData(Tuple2<String, Integer> data) {
        return data.f0;
    }

    @Override
    public String getValueFromData(Tuple2<String, Integer> data) {
        return data.f1.toString();
    }
}
