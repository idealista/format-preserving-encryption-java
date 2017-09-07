package com.idealista.fpe.algorithm.ff1;

import java.util.Random;

public class RadomValuesProvider {

    public static int getRandomKeyLength() {
        int[] keySizes = {128, 192, 256};
        int rnd = new Random().nextInt(keySizes.length);
        return keySizes[rnd];
    }


    public static int[] randomPlainText(int radix) {
        int [] a = new int[getRandomTextLength()];
        Random randomNumbers = new Random();
        for (int i=0; i<a.length; i++) {
            a[i] = randomNumbers.nextInt(radix);
        }
        return a;
    }

    public static int getRandomTextLength() {
        int[] anySizes = {2, 5, 14, 25};
        int rnd = new Random().nextInt(anySizes.length);
        return anySizes[rnd];
    }

    public static int getRandomRadix() {
        int[] anySizes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int rnd = new Random().nextInt(anySizes.length);
        return anySizes[rnd];
    }

    public static String asString(int[] value) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int item : value) {
            builder.append(item).append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public static String asString(byte[] value) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (byte item : value) {
            builder.append(item).append(", ");
        }
        builder.append("]");
        return builder.toString();
    }

    public static String valuesAsString(int[] input, byte[] key, int radix) {
        return new StringBuilder()
                .append("input: ").append(RadomValuesProvider.asString(input))
                .append("\n")
                .append("key: ").append(RadomValuesProvider.asString(key))
                .append("\n")
                .append("radix: ").append(radix).toString();
    }

}
