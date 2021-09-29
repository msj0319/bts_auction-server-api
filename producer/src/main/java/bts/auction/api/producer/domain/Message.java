package bts.auction.api.producer.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Message {
    @ApiParam(value = "nft id(topic name)", required = true)
    private String nftId;
    @ApiParam(value = "매수자(key)", required = true)
    private String email;
    @ApiParam(value = "현재 호가(value)", required = true)
    private String auctionPrice;
}
