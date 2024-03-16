package com.grandel.storj.error;

import lombok.Getter;

@Getter
public class ErrorException extends RuntimeException {
    private ErrorEnum errorEnum;

    public ErrorException(ErrorEnum errorEnum) {
        super(errorEnum.getCode() + " - " + errorEnum.getMessage());
        this.errorEnum = errorEnum;
    }
}
