package com.howbuy.onlinecalc.utils;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * 向kafka消息中心发送消息..
 * @author li.zhang
 *
 */
public class KafkaMsgSender
{
    /**
     * 向kafka发送消息..
     * @param topic 主题
     * @param message 消息内容
     */
    public static void dispatch(String topic, String message)
    {
        Properties props = new Properties();
        
        //注意这里要是hostname，不要直接写ip地址.
        props.put("metadata.broker.list","kafka:9092");
        props.put("zookeeper.connect", "zookeeper:12181,zookeeper:22181,zookeeper:32181");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("producer.type", "sync");
        props.put("compression.codec", "1");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(topic, message); //topic, message
        producer.send(data);
        
        producer.close();
        
    }
}
