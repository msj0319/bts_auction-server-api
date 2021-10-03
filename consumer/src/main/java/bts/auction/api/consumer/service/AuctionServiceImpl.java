package bts.auction.api.consumer.service;

import bts.auction.api.consumer.domain.Auction;
import bts.auction.api.consumer.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;

    @Override
    public Flux<Auction> findAll() {
        return auctionRepository.findAll();
    }
}
