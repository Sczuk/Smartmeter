package com.registerService.domain.exception.user;

import com.registerService.domain.exception.BaseException;

public class UnauthorizedUserException extends BaseException {

    private final String code = "UNAUTHORIZED_USER";
    private final int httpStatusCode = 403;

    public UnauthorizedUserException(String message) {
        super(message);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
