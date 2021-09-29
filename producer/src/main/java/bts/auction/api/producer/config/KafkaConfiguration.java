package bts.auction.api.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import reactor.core.scheduler.Schedulers;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {
    private final String host = "127.0.0.1:9092";

    @Value("${kafka.groupId}")
    private String groupId;

    private Map<String, Object> getProducerProps() {
        return new HashMap<String, Object>() {
            {
                put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
                put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
                put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 2000);
            }
        };
    }

    //카프카에 메시지 발행
    @Bean("kafkaSender")
    public KafkaSender<String, Object> kafkaSender() {
        //KafkaProducer에서 사용할 프로퍼티 지정
        SenderOptions<String, Object> senderOptions = SenderOptions.create(getProducerProps());
        //Reactive KafkaSender 발행 옵션을 설정
        senderOptions.scheduler(Schedulers.parallel()); //고정 크기 스레드 풀을 이용해 실행, 병렬 작업
        senderOptions.closeTimeout(Duration.ofSeconds(5));
        return KafkaSender.create(senderOptions);
    }

}
