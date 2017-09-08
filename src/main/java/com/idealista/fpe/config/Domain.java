package com.idealista.fpe.config;

import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

public interface Domain {
    public Alphabet alphabet();
    public TextToIntTransformer textToIntTransformer();
    public IntToTextTransformer intToTextTransformer();
}
