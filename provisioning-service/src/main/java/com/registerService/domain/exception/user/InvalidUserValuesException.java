package com.registerService.domain.exception.user;

import com.registerService.domain.exception.BaseException;

public class InvalidUserValuesException extends BaseException {

    private final String code = "INCORRECT_VALUES_USER";
    private final int httpStatusCode = 422;

    public InvalidUserValuesException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

}
