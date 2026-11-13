package com.registerService.domain.exception.user;

import com.registerService.domain.exception.BaseException;

public class UserNotFoundException extends BaseException{

    private final String code = "USER_NOT_FOUND";
    private final int httpStatusCode = 400;

    public UserNotFoundException(String message) {
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
