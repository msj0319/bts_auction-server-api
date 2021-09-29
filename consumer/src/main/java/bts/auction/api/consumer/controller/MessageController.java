package bts.auction.api.consumer.controller;

import bts.auction.api.consumer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public Flux<ServerSentEvent<Object>> consumeMessage() {
        return messageService.receive();
    }
}
