package com.registerService.infra.web.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidateExceptionResponse {

    private String detail;
    private String code;
    private int httpStatusCode;
    private List<ValidateException> errors;
}
