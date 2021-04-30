package com.sg.spark.controller;

import com.sg.spark.messages.ProducedKafkaMessage;
import com.sg.spark.utils.SGKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestKafkaController {

    @Autowired
    SGKafkaProducer SGKafkaProducer;

    @PostMapping("/kafka-inbound/message")
    public ResponseEntity<ProducedKafkaMessage> produceKafkaMessage(ProducedKafkaMessage producedKafkaMessage)   {
        SGKafkaProducer.sendInboundKafkaMessage(producedKafkaMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(producedKafkaMessage);
    }
}
