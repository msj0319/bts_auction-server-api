package bts.auction.api.producer.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    //Topic name
    private String nft_id;
    //key
    private String email;
    //value
    private String auction_price;
}
