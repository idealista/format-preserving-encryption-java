package com.idealista.fpe.config;

import java.nio.charset.Charset;

import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;
import com.idealista.fpe.component.functions.prf.PseudorandomFunction;

public class Config {

    private Integer radix = Defaults.DEFAULT_RADIX;
    private Domain domain = Defaults.DOMAIN;
    private Integer maxLength = Defaults.DEFAULT_MAX_LENGTH;
    private Integer minLength = Defaults.DEFAULT_MIN_LENGTH;

    private final PseudorandomFunction pseudorandomFunction;
    private final String key;

    public Config(String key) {
        this.key = key;
        this.pseudorandomFunction = new DefaultPseudarandomFunction(this.key.getBytes(Charset.defaultCharset()));
    }

    public Config(String key, PseudorandomFunction pseudorandomFunction) {
        this.key = key;
        this.pseudorandomFunction = pseudorandomFunction;
    }

    public Integer getRadix() {
        return radix;//domain.radix;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public PseudorandomFunction getPseudorandomFunction() {
        return pseudorandomFunction;
    }

    public String getKey() {
        return key;
    }
}
