package com.eshop.apigateway.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private int code;
    private String message;
    private String path;
    private String method;

    public ExceptionMessage() {
        timestamp = LocalDateTime.now();
    }

    public ExceptionMessage(int code, String message, String path, String method) {
        this();
        this.code = code;
        this.message = message;
        this.path = path;
        this.method = method;
    }
}
