package com.grandel.storj.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import storj.model.Error;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<Error> errorException(ErrorException e) {
        log.info("Managing Error Code: {}, Message: {}, HttpStatus: {}",
                e.getErrorEnum().getCode(),
                e.getErrorEnum().getMessage(),
                e.getErrorEnum().getHttpStatus());
        HttpStatus httpStatus = Optional.of(HttpStatus.valueOf(e.getErrorEnum().getHttpStatus()))
                .orElse(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(httpStatus)
                .body(createError(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> errorException(Exception e) {
        log.info("Managing Error {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createError(e.getMessage(), "500"));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Error> errorException(RuntimeException e) {
        log.info("Managing Error {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createError(e.getMessage(), "400"));
    }

    private Error createError(ErrorException e) {
        Error error = new Error();
        error.setCode(e.getErrorEnum().getCode());
        error.setMessage(e.getErrorEnum().getMessage());
        return error;
    }

    private Error createError(String message, String code) {
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error;
    }

}
