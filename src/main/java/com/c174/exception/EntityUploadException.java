package com.c174.exception;

public class EntityUploadException extends Exception{
    public EntityUploadException(String message) {
        super(message);
    }

    public EntityUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
