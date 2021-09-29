package bts.auction.api.producer.controller;

import bts.auction.api.producer.domain.Message;
import bts.auction.api.producer.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Api(value = "AuctionController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auction")
public class AuctionController {
    private final MessageService messageService;

    @ApiOperation(value = "카프카 클러스터에 메시지 전송")
    @PostMapping
    public Mono<String> produceMessage(@RequestBody Mono<Message> message) {
        return message
                .flatMap(msg -> messageService.send(msg.getNftId(), msg.getEmail(), msg.getAuctionPrice()));
    }
}
