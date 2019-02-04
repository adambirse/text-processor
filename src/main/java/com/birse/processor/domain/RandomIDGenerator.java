package com.birse.processor.domain;

import org.apache.commons.math3.random.RandomDataGenerator;

public class RandomIDGenerator {

    public static final long generateLong() {
        return new RandomDataGenerator().nextLong(1L, 1000L);
    }

}
