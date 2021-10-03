package bts.auction.api.producer.service;

import bts.auction.api.producer.domain.Auction;
import bts.auction.api.producer.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;

    @Override
    public Mono<Auction> save(Auction auction) {
        return auctionRepository.save(auction);
    }
}
