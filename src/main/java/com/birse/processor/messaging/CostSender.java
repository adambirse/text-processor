package com.birse.processor.messaging;

import com.birse.processor.domain.Cost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CostSender {

    private static final Logger LOG = LoggerFactory.getLogger(CostSender.class);

    @Autowired
    private KafkaTemplate<Long, Cost> kafkaTemplate;

    @Value("${app.topic.cost}")
    private String topic;

    public void send(Cost message) {
        LOG.info("sending message='{}' to topic='{}'", message, topic);
        kafkaTemplate.send(topic, message);
        LOG.info("message='{}' sent to topic='{}'", message, topic);

    }
}
