package com.idealista.fpe.config.basic;

import com.idealista.fpe.config.Alphabet;

public class BasicAlphabet implements Alphabet{

    private final char[] availableCharacters = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
            'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    @Override
    public char[] availableCharacters() {
        return availableCharacters;
    }

    @Override
    public Integer radix() {
        return availableCharacters.length;
    }
}
