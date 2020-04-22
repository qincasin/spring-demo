package com.qjx.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

/**
 * Created by qincasin on 2020/4/21.
 * kafka 生产者
 */
public class KafkaProducerAnalysis {

    public static final String brokerList = "localhost:9092";
    //    public static final String groupId ="group.demo";
    public static final String topic = "topic-demo";


    public static Properties init() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        return properties;
    }

    public static void main(String[] args) {

        Properties props = init();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello,kafka!");

        try {
            Future<RecordMetadata> send = producer.send(record);
            RecordMetadata recordMetadata = send.get();
            System.out.println(recordMetadata);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            producer.close();
        }
    }
}
