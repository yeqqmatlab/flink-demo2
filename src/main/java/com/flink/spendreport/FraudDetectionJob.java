package com.flink.spendreport;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.walkthrough.common.entity.Alert;
import org.apache.flink.walkthrough.common.entity.Transaction;
import org.apache.flink.walkthrough.common.sink.AlertSink;
import org.apache.flink.walkthrough.common.source.TransactionSource;

/**
 * created by yqq 2020/8/31
 */
public class FraudDetectionJob {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Transaction> transactions = env.addSource(new TransactionSource()).name("transactions");

        DataStream<Alert> alerts = transactions.keyBy(Transaction::getAccountId).process(new FraudDetector()).name("fraud-detector");

        alerts.addSink(new AlertSink()).name("send-alerts");

        env.execute("fraud detection");

    }
}
