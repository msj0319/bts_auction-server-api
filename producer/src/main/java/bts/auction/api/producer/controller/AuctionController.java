package bts.auction.api.producer.controller;

import bts.auction.api.producer.domain.Auction;
import bts.auction.api.producer.repository.AuctionRepository;
import bts.auction.api.producer.service.AuctionService;
import bts.auction.api.producer.service.KafkaProducer;
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
    private final KafkaProducer kafkaProducer;
    private final AuctionService auctionService;

    @PostMapping("/publish")
    public Mono<Auction> produceMessage(@RequestBody Auction auction) {
        Auction auction1 = new Auction();
        auction1.setNft_id(auction.getNft_id());
        auction1.setEmail(auction.getEmail());
        auction1.setAuctionPrice(auction.getAuctionPrice());
        //카프카 브로커로 메시지 전송
        kafkaProducer.sendMessage(auction1);
        //몽고 DB에 데이터 저장
        return auctionService.save(auction1);
    }
}
