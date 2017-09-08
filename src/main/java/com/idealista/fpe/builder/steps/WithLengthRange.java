package com.idealista.fpe.builder.steps;

public interface WithLengthRange {
    Builder withLengthRange(int min, int max);

    Builder withDefaultLengthRange();
}
