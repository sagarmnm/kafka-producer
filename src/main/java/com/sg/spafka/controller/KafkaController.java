package com.sg.spafka.controller;

import com.sg.spafka.messages.ProducedKafkaMessage;
import com.sg.spafka.utils.SGProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {

    @Autowired
    SGProducer SGProducer;

    @PostMapping("/kafka-inbound/message")
    public ResponseEntity<ProducedKafkaMessage> produceKafkaMessage(ProducedKafkaMessage producedKafkaMessage)   {
        SGProducer.sendInboundKafkaMessage(producedKafkaMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(producedKafkaMessage);
    }
}
