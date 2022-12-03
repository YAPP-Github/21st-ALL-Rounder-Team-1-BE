package com.example.holaserver.response;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseTemplate<Object> handleAdminErrorException(ResponseException exception) {
        log.error("throw customException : {}", exception.getCause());
        return new ResponseTemplate<>(exception.getStatus(), exception.getMessage());
    }
}
