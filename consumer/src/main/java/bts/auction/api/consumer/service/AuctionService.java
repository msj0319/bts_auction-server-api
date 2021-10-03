package bts.auction.api.consumer.service;

import bts.auction.api.consumer.domain.Auction;
import reactor.core.publisher.Flux;

public interface AuctionService {
    Flux<Auction> findAll();
}
