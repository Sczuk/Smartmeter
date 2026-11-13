package com.registerService.infra.security.exception;

import com.registerService.domain.exception.BaseException;

public class LoginUnauthorizedException extends BaseException{

    private final String code = "LOGIN_UNAUTHORIZED";
    private final int httpStatusCode = 401;

    public LoginUnauthorizedException(String message) {
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
