package com.registerService.infra.web.handler.dto;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private String code;
    private int httpStatusCode;
    private String message;

    public ExceptionResponse(String message, String code, int httpStatusCode) {
        this.message = message;
        this.code = code;
        this.httpStatusCode = httpStatusCode;
    }

    public String getCode() {
        return this.code;
    }

    public int getHttpStatusCode() {
        return this.httpStatusCode;
    }

}
