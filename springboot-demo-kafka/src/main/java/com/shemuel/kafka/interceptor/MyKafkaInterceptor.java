package com.shemuel.kafka.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @date: 2020/2/21 22:19
 * @author: dengshaoxiang
 * @description:
 */
public class MyKafkaInterceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord record) {

        return new ProducerRecord(record.topic(),record.key(),record.value()+"interceptor");
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        System.out.println(recordMetadata.topic());
        System.out.println("确认");
    }

    @Override
    public void close() {
        System.out.println("关闭");
    }

    @Override
    public void configure(Map<String, ?> map) {
        System.out.println("配置");
        System.out.println(map);
    }
}
