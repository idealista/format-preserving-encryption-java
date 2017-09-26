package com.idealista.fpe.component.functions.prf;

import java.security.GeneralSecurityException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DefaultPseudoRandomFunction implements PseudoRandomFunction {

    private static final String CIPHER_ALGORITHM = "AES/CBC/NoPadding";
    private static final String KEY_ALGORITHM_NAME = "AES";

    private final byte[] key;
    private byte[] initializationVector = initializationVector();

    public DefaultPseudoRandomFunction(byte[] key){
        this.key = key;
    }

    public byte[] apply(byte[] plain) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(this.key, KEY_ALGORITHM_NAME), new IvParameterSpec(initializationVector));
            byte[] result = cipher.doFinal(plain);
            return Arrays.copyOfRange(result, result.length - initializationVector.length, result.length);
        } catch (GeneralSecurityException e) {
            throw new SecurityException(e);
        }

    }

    private static byte[] initializationVector() {
        byte[] initializationVector = new byte[16];
        for (int i = 0; i < initializationVector.length; i++) {
            initializationVector[i] = (byte) 0x00;
        }
        return initializationVector;
    }
}
