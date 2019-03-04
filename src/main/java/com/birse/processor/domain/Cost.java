package com.birse.processor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Domain class used by Kafka
 */
public class Cost {

    private Long id;

    private LocalDateTime time;

    private BigDecimal cost;

    public Cost() {
        id = RandomIDGenerator.generateLong();
    }
    public Cost(LocalDateTime time, BigDecimal cost ) {
        id = RandomIDGenerator.generateLong();
        this.time = time;
        this.cost = cost;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
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
