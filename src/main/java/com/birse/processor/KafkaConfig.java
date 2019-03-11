package com.birse.processor;

import com.birse.processor.domain.Cost;
import com.birse.processor.domain.Text;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${app.kafka.bootstrap-servers}")
    private String servers;

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new Jdk8Module());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper;
    }

    @Bean
    public ConsumerFactory<Long, Text> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                servers);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "processor");
        return new DefaultKafkaConsumerFactory<>(props, new LongDeserializer(), new JsonDeserializer<>(Text.class, getObjectMapper()));
    }

    @Bean
    public ProducerFactory<Long, Cost> producerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "kafka1:9092,kafka2:9092,kafka3:9092");

        return new DefaultKafkaProducerFactory<>(props, new LongSerializer(), new JsonSerializer<>(getObjectMapper()));
    }

    @Bean
    public KafkaTemplate<Long, Cost> kafkaTemplate() {
        return new KafkaTemplate<Long,Cost>(producerFactory());
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
