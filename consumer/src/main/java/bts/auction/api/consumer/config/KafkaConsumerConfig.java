package bts.auction.api.consumer.config;

import bts.auction.api.consumer.domain.Auction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.consumer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, Auction> auctionConsumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "auction-group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "bts.auction.api.consumer.domain");
        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
               new ErrorHandlingDeserializer<>(new JsonDeserializer<>(Auction.class, false)));
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Auction> auctionKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Auction> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(auctionConsumerFactory());
        return factory;
    }
}
