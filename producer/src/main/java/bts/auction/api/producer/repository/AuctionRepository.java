package bts.auction.api.producer.repository;

import bts.auction.api.producer.domain.Auction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AuctionRepository extends ReactiveMongoRepository<Auction, String> {
}
