package com.birse.processor.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * Domain class used by Kafka
 */
public class Text {

    private Long id;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime time;

    private String text;

    public Text() {
        id = RandomIDGenerator.generateLong();
    }
    public Text(String text) {
        id = RandomIDGenerator.generateLong();
        this.text = text;
    }

    public Text(Long id, LocalDateTime time, String text) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Text{" +
                "id=" + id +
                ", time=" + time +
                ", text='" + text + '\'' +
                '}';
    }
}
