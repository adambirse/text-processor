package com.birse.processor;

import com.birse.processor.domain.Text;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@DirtiesContext
public abstract class KafkaProducerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerTest.class);

    private static String SENDER_TOPIC = "text";

    private KafkaMessageListenerContainer<Long, Text> container;

    protected BlockingQueue<ConsumerRecord<Long, Text>> records;

    @ClassRule
    public static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, true, SENDER_TOPIC);


    @Before
    public void setUp() throws Exception {
        // set up the Kafka consumer properties
        Map<String, Object> consumerProperties =
                KafkaTestUtils.consumerProps("sender", "false", embeddedKafka);


        JsonDeserializer deserializer = new JsonDeserializer(Text.class);
        // create a Kafka consumer factory
        DefaultKafkaConsumerFactory<Long, Text> consumerFactory =
                new DefaultKafkaConsumerFactory<Long, Text>(consumerProperties, new LongDeserializer(), deserializer);


        // set the topic that needs to be consumed
        ContainerProperties containerProperties = new ContainerProperties(SENDER_TOPIC);

        // create a Kafka MessageListenerContainer

        container = new KafkaMessageListenerContainer<Long, Text>(consumerFactory, containerProperties);

        // create a thread safe queue to store the received message
        records = new LinkedBlockingQueue<>();

        // setup a Kafka message listener
        container.setupMessageListener(new MessageListener<Long, Text>() {
            @Override
            public void onMessage(ConsumerRecord<Long, Text> record) {
                LOGGER.debug("test-listener received message='{}'", record.toString());
                records.add(record);
            }
        });

        // start the container and underlying message listener
        container.start();

        // wait until the container has the required number of assigned partitions
        ContainerTestUtils.waitForAssignment(container, embeddedKafka.getPartitionsPerTopic());
    }

    @After
    public void tearDown() {
        container.stop();
    }
}






