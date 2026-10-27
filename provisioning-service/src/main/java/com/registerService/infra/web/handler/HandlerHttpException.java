package com.registerService.infra.web.handler;


import com.registerService.domain.exception.BaseException;
import com.registerService.infra.web.handler.dto.ExceptionResponse;
import com.registerService.infra.web.handler.dto.ValidateException;
import com.registerService.infra.web.handler.dto.ValidateExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandlerHttpException {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handlerRuntimeException(BaseException ex){
        ExceptionResponse response = new ExceptionResponse(
                ex.getMessage(),
                ex.getCode(),
                ex.getHttpStatusCode()
        );
        return ResponseEntity.status(response.getHttpStatusCode()).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidateExceptionResponse> handlerValidationException(MethodArgumentNotValidException valid){
        List<ValidateException> validateException = valid.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidateException(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();
        ValidateExceptionResponse errorResponse = new ValidateExceptionResponse(valid.getBody().getDetail(),  ValidateException.getCode(), ValidateException.getHttpStatusCode(), validateException);
        return ResponseEntity.status(ValidateException.getHttpStatusCode()).body(errorResponse);
    }


}
