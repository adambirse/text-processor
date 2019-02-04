package com.birse.processor.messaging;

import com.birse.processor.domain.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class TextReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(TextReceiver.class);

    @KafkaListener(topics = "${app.topic.text}")
    public void receive(@Payload Text message,
                        @Headers MessageHeaders headers) {
        LOG.error("received message='{}'", message.toString());
        headers.keySet().forEach(key -> LOG.info("{}: {}", key, headers.get(key)));
    }
}
