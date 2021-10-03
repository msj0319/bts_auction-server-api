package bts.auction.api.consumer.repository;

import bts.auction.api.consumer.domain.Auction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AuctionRepository extends ReactiveMongoRepository<Auction, String> {
    @Query("{'title': {$regex: ?0}}")
    Flux<Auction> findRegexByNftId(String nftId);
}
