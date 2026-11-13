package com.registerService.domain.exception.user;

import com.registerService.domain.exception.BaseException;

public class UserExistsException extends BaseException {

    private final String code = "USER_EXISTS";
    private final int httpStatusCode = 409;

    public UserExistsException(String message) {
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
