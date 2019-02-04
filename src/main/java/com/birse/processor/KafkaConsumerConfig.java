package com.birse.processor;

import com.birse.processor.domain.Text;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public ConsumerFactory<Long, Text> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "kafka1:9092,kafka2:9092,kafka3:9092");
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "processor");
        return new DefaultKafkaConsumerFactory<>(props, new LongDeserializer(), new JsonDeserializer<>(Text.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, Text>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<Long, Text> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
