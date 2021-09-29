package bts.auction.api.producer.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;

import javax.annotation.PreDestroy;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService{

    private final KafkaSender<String, Object> kafkaSender;
    private Disposable disposable;

    @PreDestroy
    public void destroy() {
        if (disposable != null) {
            //Observable 에게 더 이상 데이터를 발행하지 않도록 구독 해지
            disposable.dispose();
        }
        kafkaSender.close();
    }

    @Override
    public Mono<Boolean> send(String topic, String key, Object value) {
        return kafkaSender.createOutbound()
                .send(Mono.just(new ProducerRecord<>(topic, key, value)))
                .then()
                //메시지 보내기에 성공하면 true 반환
                .map(ret -> true)
                //실패하면 false 반환
                .onErrorResume(e -> {
                    System.out.println("Kafka Send Error");
                    return Mono.just(false);
                });
    }
}
