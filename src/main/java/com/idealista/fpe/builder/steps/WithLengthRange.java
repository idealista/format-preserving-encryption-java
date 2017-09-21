package com.idealista.fpe.builder.steps;

import com.idealista.fpe.config.LengthRange;

public interface WithLengthRange {
    Builder withLengthRange(LengthRange lengthRange);

    Builder withDefaultLengthRange();
}
