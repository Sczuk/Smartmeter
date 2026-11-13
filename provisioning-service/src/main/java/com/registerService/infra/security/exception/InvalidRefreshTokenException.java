package com.registerService.infra.security.exception;

import com.registerService.domain.exception.BaseException;

public class InvalidRefreshTokenException extends BaseException {

    private final String code = "INVALID_REFRESH_TOKEN";
    private final int httpStatusCode = 401;

    public InvalidRefreshTokenException(String message) {
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
