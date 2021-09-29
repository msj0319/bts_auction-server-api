package bts.auction.api.producer.service;

import bts.auction.api.producer.domain.KafkaException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final Sinks.Many<Object> sinksMany;
    private final KafkaService kafkaService;
    private final ObjectMapper objectMapper;

    @Value("${kafka.topic}")
    String topic;

    @Override
    public Mono<String> send(String topic, String key, Object value) {
        this.topic = topic;
        try {
            return kafkaService.send(topic, key, objectMapper.writeValueAsString(value))
                    .map(b -> {
                        if (b) {
                            return "success send message";
                        } else {
                            return "fail send message";
                        }
                    });
        } catch (JsonProcessingException e) {
            return Mono.error(KafkaException.SEND_ERROR);
        }
    }
}
