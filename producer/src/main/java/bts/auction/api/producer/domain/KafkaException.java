package bts.auction.api.producer.domain;

public class KafkaException extends RuntimeException {
    public static final KafkaException SEND_ERROR = new KafkaException("Sending Failure");

    public KafkaException(String message) {
        super(message);
    }
}
