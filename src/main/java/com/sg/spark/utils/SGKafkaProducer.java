package com.sg.spark.utils;

import com.sg.spark.messages.ProducedKafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class SGKafkaProducer {
    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;

    public void sendInboundKafkaMessage(ProducedKafkaMessage producedKafkaMessage)   {
        System.out.println("ProducedKafkaMessage : "+ producedKafkaMessage);

        ListenableFuture lf = kafkaTemplate.send("sgtopic", producedKafkaMessage.getKey(), producedKafkaMessage.getValue());

        lf.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("SGKafkaProducer Exception: " + throwable.toString());
            }
            @Override
            public void onSuccess(Object o) {
                System.out.println("SGKafkaProducer kafka send success: " + o.toString());
            }
        });
    }
}
