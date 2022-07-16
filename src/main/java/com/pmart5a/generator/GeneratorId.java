package com.pmart5a.generator;

public class GeneratorId {
    private long nextId = 1;
    private static GeneratorId generatorId = null;

    private GeneratorId() {}

    public static GeneratorId getGeneratorId() {
        if (generatorId == null) {
            generatorId = new GeneratorId();
        }
        return generatorId;
    }

    public synchronized Long getId() {
        return nextId++;
    }
}
