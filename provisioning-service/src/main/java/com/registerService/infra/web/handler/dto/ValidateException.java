package com.registerService.infra.web.handler.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ValidateException {

    private static final String code = "FIELD_VALIDATE_ERROR";
    private static final int httpStatusCode = HttpStatus.BAD_REQUEST.value();
    private String field;
    private String messageValidate;


    public ValidateException(String field, String messageValidate) {
        this.field = field;
        this.messageValidate = messageValidate;
    }

    public static int getHttpStatusCode() {
        return httpStatusCode;
    }

    public static String getCode() {
        return code;
    }
}
