package bts.auction.api.producer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

//Spring WebFlux Server Sent Event
//SSE Event-Stream
@Configuration
public class SseConfig {

    //Publisher 와 Subscriber 에 모두 동작하는 Reactor Sink 객체
    //Object를 받으면 Subscriber에게 전달
    //Reactor Sink는 브라우저의 Publisher 처럼 동작하고 Event Stream을 통해 브라우저에 Object 전달
    @Bean
    public Sinks.Many<Object> sinksMany() {
        return Sinks
                .many()
                .multicast()
                .directBestEffort();
    }
}
