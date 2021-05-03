package com.sg.spark.utils;

import com.sg.spark.messages.ProducedKafkaMessage;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Log4j2
public class SGKafkaProducer {
    @Autowired
    KafkaTemplate<Long, String> kafkaTemplate;

    public void sendInboundKafkaMessage(ProducedKafkaMessage producedKafkaMessage)   {
    	log.debug("sendInboundKafkaMessage: "+ producedKafkaMessage);
    	Long key = 123l;
		String value = "abc def hig";
		
    	if(producedKafkaMessage!=null) {
    		 key = producedKafkaMessage.getKey();
    		value = producedKafkaMessage.getValue();
    	}

        ListenableFuture lf = kafkaTemplate.send("sgtopic", key, value);

        lf.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
            }
            @Override
            public void onSuccess(Object o) {
            	  log.debug("SGKafkaProducer: " +o.toString());
            }
        });
    }
}
