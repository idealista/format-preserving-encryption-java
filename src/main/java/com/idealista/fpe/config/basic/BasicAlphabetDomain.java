package com.idealista.fpe.config.basic;

import com.idealista.fpe.config.Alphabet;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

public class BasicAlphabetDomain implements Domain{


    private Alphabet alphabet = new BasicAlphabet();
    private BasicDomainTransformations transformations = new BasicDomainTransformations(alphabet.availableCharacters());

    @Override
    public Alphabet alphabet() {
        return alphabet;
    }

    @Override
    public TextToIntTransformer textToIntTransformer() {
        return transformations;
    }

    @Override
    public IntToTextTransformer intToTextTransformer() {
        return transformations;
    }
}
