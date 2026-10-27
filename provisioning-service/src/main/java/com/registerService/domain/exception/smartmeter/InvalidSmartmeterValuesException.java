package com.registerService.domain.exception.smartmeter;

import com.registerService.domain.exception.BaseException;

public class InvalidSmartmeterValuesException extends BaseException {

    private final String code = "INCORRECT_VALUES_SMARTMETER";
    private final int httpStatusCode = 422;

    public InvalidSmartmeterValuesException(String message) {
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
