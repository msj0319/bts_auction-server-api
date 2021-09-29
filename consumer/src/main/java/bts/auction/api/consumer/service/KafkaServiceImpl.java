package bts.auction.api.consumer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Sinks;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.ReceiverRecord;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class KafkaServiceImpl implements KafkaService {
    private final ReceiverOptions<String, Object> receiverOptions;
    private final Sinks.Many<Object> sinksMany;

    private Disposable disposable;

    @PostConstruct
    public void init() {    //Consumer 를 열어 놓음
        disposable = KafkaReceiver
                .create(receiverOptions)
                .receive()
                .doOnNext(e -> {
                    //에러 발생 시, Consumer 가 종료되고 재시작할 방법이 없기 때문에 재시작하도록 처리
                    System.out.println("Kafka READ Error");
                    init();
                })
                .subscribe();
    }

    private Consumer<ReceiverRecord<String, Object>> processReceiveData() {
        return r -> {
            System.out.println("Kafka Consuming");
            Object receiveKey = r.key();    //key
            Object receiveValue = r.value(); //value
            if (receiveKey != null && receiveValue != null) {
                //소비하려는 Data가 null이 아니면, ReceiverRecord에서 다음 값 가져오기
                //null이면 즉시 실패 처리를 트리거한다.
                sinksMany.emitNext(r.key(),Sinks.EmitFailureHandler.FAIL_FAST);
                sinksMany.emitNext(r.value(), Sinks.EmitFailureHandler.FAIL_FAST);
            }
            r.receiverOffset().acknowledge();
        };
    }
}
