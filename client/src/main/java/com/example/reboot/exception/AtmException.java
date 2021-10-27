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

    public AtmException(String message, Throwable cause) {
        super(message, cause);
    }

    public AtmException(Throwable cause) {
        super(cause);
    }

    public AtmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
