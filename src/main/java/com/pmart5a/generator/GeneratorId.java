package com.pmart5a.generator;

import java.util.concurrent.atomic.AtomicLong;

public class GeneratorId {
    private final AtomicLong nextId = new AtomicLong(1);

    private GeneratorId() {
    }

    private static class GeneratorIdHolder {
        public static final GeneratorId generatorId = new GeneratorId();
    }

    public static GeneratorId getGeneratorId() {
        return GeneratorIdHolder.generatorId;
    }

    public Long getId() {
        return nextId.getAndIncrement();
    }
}