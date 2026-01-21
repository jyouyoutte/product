package com.jonathan.exception;

public class RationalException extends IllegalArgumentException{
    public RationalException() {
        this("Denominator cannot be zero.");
    }

    public RationalException(String s) {
        super(s);
    }

    public RationalException(String message, Throwable cause) {
        super(message, cause);
    }

    public RationalException(Throwable cause) {
        super(cause);
    }
}
