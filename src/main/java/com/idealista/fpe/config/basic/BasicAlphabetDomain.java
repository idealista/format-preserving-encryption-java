package com.idealista.fpe.config.basic;

import com.idealista.fpe.config.Alphabet;
import com.idealista.fpe.config.Domain;

public class BasicAlphabetDomain implements Domain{


    private Alphabet alphabet = new BasicAlphabet();
    private BasicDomainTransformations transformations = new BasicDomainTransformations(alphabet.availableCharacters());

    @Override
    public Alphabet alphabet() {
        return alphabet;
    }

    @Override
    public int[] transform(String data) {
        return transformations.transform(data);
    }

    @Override
    public String transform(int[] data) {
        return transformations.transform(data);
    }
}
