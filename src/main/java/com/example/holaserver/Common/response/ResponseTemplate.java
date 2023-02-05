package com.example.holaserver.Common.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
@JsonPropertyOrder({"code","message","status"})
public class ResponseTemplate<T> {
    private final String message;
    private final int status;
    private T data;

    //요청 성공 시
    public ResponseTemplate(T data, String message) {
        this.message = message;
        this.status = HttpStatus.OK.value();
        this.data = data;
    }

    //요청 실패 시
    public ResponseTemplate(HttpStatus status, String message) {
        this.message = message + ", ERROR : " + status.getReasonPhrase();
        this.status = status.value();
    }
}
