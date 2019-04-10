package com.birse.processor.domain;

import java.time.LocalDate;

/**
 * Domain class used by Kafka
 */
public class Text {

    private Long id;

    private LocalDate date;

    private String text;

    public Text() {
        id = RandomIDGenerator.generateLong();
    }

    public Text(String text) {
        id = RandomIDGenerator.generateLong();
        this.text = text;
    }

    public Text(Long id, LocalDate date, String text) {
        this.id = id;
        this.date = date;
        this.text = text;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
