package com.c174.exception;

public class EntityDeleteException extends Exception{
    public EntityDeleteException(String message) {
        super(message);
    }

    public EntityDeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
