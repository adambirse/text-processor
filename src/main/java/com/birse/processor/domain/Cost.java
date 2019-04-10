package com.birse.processor.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Domain class used by Kafka
 */
public class Cost {

    private Long id;

    private LocalDate time;

    private BigDecimal cost;

    public Cost() {
        id = RandomIDGenerator.generateLong();
    }

    public Cost(LocalDate time, BigDecimal cost) {
        id = RandomIDGenerator.generateLong();
        this.time = time;
        this.cost = cost;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
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
                ", time=" + time +
                ", cost=" + cost +
                '}';
    }
}
