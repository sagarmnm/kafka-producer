package com.sg.spark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sg.spark.messages.ProducedKafkaMessage;
import com.sg.spark.utils.SGKafkaProducer;
import lombok.extern.log4j.Log4j2;

/**
 * 
 * http://localhost:8001/echo
 * http://localhost:8001/kafka-inbound/message
 * 
 * @author NS
 *
 */
@RestController
@Log4j2
public class RestKafkaController {

    @Autowired
    SGKafkaProducer SGKafkaProducer;

    @PostMapping("/kafka-inbound/message")
    public ResponseEntity<ProducedKafkaMessage> produceKafkaMessage(ProducedKafkaMessage producedKafkaMessage)   {
    	log.debug("produceKafkaMessage: "+producedKafkaMessage );
        SGKafkaProducer.sendInboundKafkaMessage(producedKafkaMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(producedKafkaMessage);
    }
    
	@GetMapping(path ="/echo" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public String echo() {
        log.debug("RestKafkaController echo: " );
         return "Service is up!";
	}
}
