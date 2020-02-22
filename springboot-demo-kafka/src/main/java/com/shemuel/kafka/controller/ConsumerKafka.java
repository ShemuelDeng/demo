package com.shemuel.kafka.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @date: 2020/2/21 20:58
 * @author: dengshaoxiang
 * @description:
 */
@Component
public class ConsumerKafka {
    @KafkaListener(topics = {"shemuel"},groupId = "default_consumer_group")
    public void listener(ConsumerRecord record){
        System.out.println("value:"+record.value());
        System.out.println("header:"+record.headers());
        System.out.println("key:"+record.key());
        System.out.println("leaderEpoch:"+record.leaderEpoch());
        System.out.println("partition:"+record.partition());
        System.out.println("serializedKeySize:"+record.serializedKeySize());
        System.out.println("serializedValueSize:"+record.serializedValueSize());
    }
}
