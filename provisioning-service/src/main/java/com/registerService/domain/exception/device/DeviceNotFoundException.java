package com.registerService.domain.exception.device;

import com.registerService.domain.exception.BaseException;

public class DeviceNotFoundException extends BaseException {

    private final String code = "DEVICE_NOT_FOUND";
    private final int httpStatusCode = 400;

    public DeviceNotFoundException(String message) {
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
