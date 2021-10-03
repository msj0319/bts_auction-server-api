package bts.auction.api.consumer.controller;

import bts.auction.api.consumer.domain.Auction;
import bts.auction.api.consumer.service.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
public class AuctionController {
    private final AuctionService auctionService;

    @GetMapping("auction/consumed")
    public Flux<Auction> consumedMessage() {
        return auctionService.findAll();
    }
}
