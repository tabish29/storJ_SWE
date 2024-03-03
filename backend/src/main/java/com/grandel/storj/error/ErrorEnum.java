package com.grandel.storj.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    UTENTENOTFOUND("UtenteNotFound", "Utente non registrato!" , 404),
    UTENTEALREADYSIGNED("UtenteAlreadySigned", "Utente gia' registrato!" , 400);

    private String code;
    private String message;
    private int httpStatus;
}
