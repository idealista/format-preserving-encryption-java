package com.idealista.fpe.config;

import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

public class GenericDomain implements Domain{

    private final Alphabet alphabet;
    private final TextToIntTransformer textToIntTransformer;
    private final IntToTextTransformer intToTextTransformer;

    public GenericDomain(Alphabet alphabet, TextToIntTransformer textToIntTransformer, IntToTextTransformer intToTextTransformer) {
        this.alphabet = alphabet;
        this.textToIntTransformer = textToIntTransformer;
        this.intToTextTransformer = intToTextTransformer;
    }

    @Override
    public Alphabet alphabet() {
        return alphabet;
    }

    @Override
    public int[] transform(String data) {
        return textToIntTransformer.transform(data);
    }

    @Override
    public String transform(int[] data) {
        return intToTextTransformer.transform(data);
    }
}
