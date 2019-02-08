package com.birse.processor.messaging;

import com.birse.processor.domain.Text;
import com.birse.processor.processor.TextProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;


@Service
public class TextReceiver {

    @Autowired
    private TextProcessor processor;

    private static final Logger LOG = LoggerFactory.getLogger(TextReceiver.class);

    @KafkaListener(topics = "${app.topic.text}")
    public void receive(@Payload Text message,
                        @Headers MessageHeaders headers) {
        LOG.debug("received message='{}'", message.toString());
        processor.process(message);
    }
}
