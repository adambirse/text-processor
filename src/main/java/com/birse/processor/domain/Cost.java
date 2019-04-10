package com.birse.processor.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Domain class used by Kafka
 */
public class Cost {

    private Long id;

    private LocalDate date;

    private BigDecimal cost;

    public Cost() {
        id = RandomIDGenerator.generateLong();
    }

    public Cost(LocalDate date, BigDecimal cost) {
        id = RandomIDGenerator.generateLong();
        this.date = date;
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Cost{" +
                "id=" + id +
                ", date=" + date +
                ", cost=" + cost +
                '}';
    }
}
