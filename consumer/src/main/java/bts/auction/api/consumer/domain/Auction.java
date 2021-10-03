package bts.auction.api.consumer.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "auction")
@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Auction implements Serializable {
    private @Id
    String a_id;
    private String nft_id;
    private String email;
    private int auctionPrice;

    @Builder
    public Auction(String nft_id, String email, int auctionPrice) {
        this.nft_id = nft_id;
        this.email = email;
        this.auctionPrice = auctionPrice;
    }
}
