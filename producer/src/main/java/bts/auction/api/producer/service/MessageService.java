package bts.auction.api.producer.service;

import reactor.core.publisher.Mono;

public interface MessageService {
    Mono<String> send(String topic, String key, Object value);
}
