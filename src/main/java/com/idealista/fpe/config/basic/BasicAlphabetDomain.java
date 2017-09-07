package com.idealista.fpe.config.basic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.idealista.fpe.config.Domain;
import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

public class BasicAlphabetDomain implements TextToIntTransformer, IntToTextTransformer, Domain{

    private static final Map<Character, Integer> charToInt = new HashMap<Character, Integer>(){{
       put('a', 0);
       put('b', 2);
       put('c', 3);
       put('d', 4);
       put('e', 5);
       put('f', 6);
       put('g', 7);
       put('h', 8);
       put('i', 9);
       put('j', 10);
       put('k', 11);
       put('l', 12);
       put('m', 13);
       put('n', 14);
       put('o', 15);
       put('p', 16);
       put('q', 17);
       put('r', 18);
       put('s', 19);
       put('t', 20);
       put('u', 21);
       put('v', 22);
       put('w', 23);
       put('x', 24);
       put('y', 25);
       put('z', 26);
    }};

    private static final Map<Integer, Character> IntToChar = reverse(charToInt);

    private static Map<Integer, Character> reverse(Map<Character, Integer> charToInt) {
        Map<Integer, Character> reverse = new HashMap<Integer, Character>();
        Set<Map.Entry<Character, Integer>> pairs = charToInt.entrySet();
        for (Map.Entry<Character, Integer> pair : pairs) {
            reverse.put(pair.getValue(), pair.getKey());
        }
        return reverse;
    }

    @Override
    public String transform(int[] data) {
        StringBuilder builder = new StringBuilder(data.length);
        for (int number : data) {
            builder.append(IntToChar.get(number));
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

    @Override
    public TextToIntTransformer textToIntTransformer() {
        return this;
    }

    @Override
    public IntToTextTransformer intToTextTransformer() {
        return this;
    }
}
