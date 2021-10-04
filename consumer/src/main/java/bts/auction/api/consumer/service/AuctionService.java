package bts.auction.api.consumer.service;

import bts.auction.api.consumer.domain.Auction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuctionService {
    Flux<Auction> findAll();
    Flux<Auction> findByNftId(String nftid);

}
