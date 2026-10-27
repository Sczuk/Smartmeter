package com.registerService.domain.exception;

public abstract class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public abstract String getCode();

    public abstract int getHttpStatusCode();

}

