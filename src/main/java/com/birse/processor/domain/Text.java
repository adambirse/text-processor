package com.birse.processor.domain;

import java.time.LocalDate;

/**
 * Domain class used by Kafka
 */
public class Text {

    private Long id;

    private LocalDate time;

    private String text;

    public Text() {
        id = RandomIDGenerator.generateLong();
    }

    public Text(String text) {
        id = RandomIDGenerator.generateLong();
        this.text = text;
    }

    public Text(Long id, LocalDate time, String text) {
        this.id = id;
        this.time = time;
        this.text = text;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
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
