package bts.auction.api.consumer.controller;

import bts.auction.api.consumer.domain.Auction;
import bts.auction.api.consumer.service.AuctionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Api(value = "Auction Consumer Controller")
@RequiredArgsConstructor
@RestController
public class AuctionController {
    private final AuctionService auctionService;

    @ApiOperation(value = "모든 NFT의 경매 매수 참여 정보 조회")
    @GetMapping("auction/consumed")
    public Flux<Auction> consumedMessage() {
        return auctionService.findAll();
    }
}
