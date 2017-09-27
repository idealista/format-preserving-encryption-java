package com.idealista.fpe.config;

import java.util.HashMap;
import java.util.Map;

import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

public class GenericTransformations implements IntToTextTransformer, TextToIntTransformer {

    private final Map<Integer, Character> intToChar;
    private final Map<Character, Integer> charToInt;

    public GenericTransformations(char[] characters){
        intToChar = new HashMap<Integer, Character>(characters.length);
        charToInt = new HashMap<Character, Integer>(characters.length);
        for (int i = 0; i < characters.length; i++) {
            intToChar.put(i, characters[i]);
            charToInt.put(characters[i], i);
        }
    }

    @Override
    public String transform(int[] data) {
        StringBuilder builder = new StringBuilder(data.length);
        for (int number : data) {
            builder.append(intToChar.get(number));
        }
        return builder.toString();
    }

    @Override
    public int[] transform(String text) {
        char[] chars = text.toCharArray();
        int[] result = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            result[i] = charToInt.get(chars[i]);
        }
        return result;
    }
}
