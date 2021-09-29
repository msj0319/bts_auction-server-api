package bts.auction.api.producer.controller;

import bts.auction.api.producer.domain.Message;
import bts.auction.api.producer.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final MessageService messageService;

    @PostMapping
    public Mono<String> produceMessage(@RequestBody Mono<Message> message) {
        return message
                .flatMap(msg -> messageService.send(msg.getNft_id(), msg.getEmail(), msg.getAuctionPrice()));
    }
}
