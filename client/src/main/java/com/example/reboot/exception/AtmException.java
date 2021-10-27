package com.example.reboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AtmException extends RuntimeException {
    public AtmException() {
    }

    public AtmException(String message) {
        super(message);
    }
}
