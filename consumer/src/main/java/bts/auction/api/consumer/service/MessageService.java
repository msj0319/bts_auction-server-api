package bts.auction.api.consumer.service;

import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface MessageService {
    Flux<ServerSentEvent<Object>> receive();
}
