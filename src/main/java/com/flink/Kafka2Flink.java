package com.flink;

import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.redis.RedisSink;
import org.apache.flink.streaming.connectors.redis.common.config.FlinkJedisPoolConfig;


import java.util.Properties;

/**
 * kafka to redis
 * created by yqq 2020/8/20
 */
public class Kafka2Flink {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000);
        env.setParallelism(1);
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","ip239:9092,ip247:9092,ip248:9092");
        properties.setProperty("group.id","test-group-01");

        FlinkKafkaConsumer011<String> consumer011 = new FlinkKafkaConsumer011<>("testkafka", new SimpleStringSchema(), properties);
        //source
        DataStream<String> stream = env.addSource(consumer011);
        DataStream<Tuple2<String, Integer>> wordCount = stream.flatMap(new LineSplitter()).keyBy(0).sum(1);

        wordCount.print();
        FlinkJedisPoolConfig config = new FlinkJedisPoolConfig.Builder()
                                                                    .setHost("192.168.1.240")
                                                                    .setPort(6379)
                                                                    .setDatabase(12)
                                                                    .build();
        //sink
        wordCount.addSink(new RedisSink<Tuple2<String,Integer>>(config,new RedisExampleMapper()));

        env.execute("flink kafka demo");
    }
}
