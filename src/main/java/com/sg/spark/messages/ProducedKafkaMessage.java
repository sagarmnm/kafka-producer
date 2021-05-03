package com.sg.spark.messages;

import lombok.ToString;

@ToString
public class ProducedKafkaMessage {
    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    Long key;
    String value;
}
