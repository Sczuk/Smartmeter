package com.registerService.domain.exception.device;

import com.registerService.domain.exception.BaseException;

public class InvalidDeviceValuesException extends BaseException {

    private final String code = "INCORRECT_VALUES_DEVICE";
    private final int httpStatusCode = 422;


    public InvalidDeviceValuesException(String message) {
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
