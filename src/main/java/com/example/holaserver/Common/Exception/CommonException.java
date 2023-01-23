package com.example.holaserver.Common.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CommonException {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public static class NotFoundStoreIdException extends Exception {}
}
