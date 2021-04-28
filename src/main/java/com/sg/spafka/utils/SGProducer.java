package com.sg.spafka.utils;

import com.sg.spafka.messages.ProducedKafkaMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class SGProducer {
    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;

    public void sendInboundKafkaMessage(ProducedKafkaMessage producedKafkaMessage)   {

        Long key = producedKafkaMessage.getKey();
        String value = producedKafkaMessage.getValue();

        ListenableFuture lf = kafkaTemplate.send("sgtopic", key, value);

        lf.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
            }
            @Override
            public void onSuccess(Object o) {
                System.out.println(o.toString());
            }
        });
    }
}
