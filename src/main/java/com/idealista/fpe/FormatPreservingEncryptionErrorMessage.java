package com.idealista.fpe;

enum FormatPreservingEncryptionErrorMessage {
    INVALID_SIZE("invalid size, out of range: "),
    TWEAK_INVALID_SIZE("Tweak length is greater than  "),
    NULL_INPUT("not valid null as input");

    private final String message;

    FormatPreservingEncryptionErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
