package com.c174.exception;

public class NoBodyException extends Exception{
    public NoBodyException(String message) {
        super(message);
    }

    public NoBodyException(String message, Throwable cause) {
        super(message, cause);
    }
}
