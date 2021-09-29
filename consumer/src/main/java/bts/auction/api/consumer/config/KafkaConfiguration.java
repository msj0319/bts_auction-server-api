package bts.auction.api.consumer.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.ReceiverOptions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    private final String host = "127.0.0.1:9092";
    @Value("${kafka.topic}")
    private String topic;
    @Value("${kafka.groupId}")
    private String groupId;

    @Bean
    public ReceiverOptions<String, Object> receiverOptions() {
        return ReceiverOptions.<String, Object>create(getConsumerProps())
                .subscription(Collections.singleton(topic));
    }

    private Map<String, Object> getConsumerProps() {
        return new HashMap<String, Object>() {{
            put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
            put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
            put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        }};
    }
}
