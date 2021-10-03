package bts.auction.api.consumer.service;

import bts.auction.api.consumer.domain.Auction;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    @KafkaListener(topics = "auction", groupId = "auction-group_id", containerFactory = "auctionKafkaListenerFactory")
    public void consume(Auction auction) {
        System.out.println("Consumed message : " + "\n{\n"
                + "\tnft_id: " + auction.getNft_id() + ", \n"
                + "\temail: " + auction.getEmail() + ", \n"
                + "\tauction_price: " + auction.getAuctionPrice() + "\n}");
    }
}
