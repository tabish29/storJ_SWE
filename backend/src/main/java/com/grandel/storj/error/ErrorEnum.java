package com.grandel.storj.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorEnum {

    UTENTENOTFOUND("UtenteNotFound", "Utente non registrato!" , 404),
    UTENTEALREADYSIGNED("UtenteAlreadySigned", "Utente già registrato!" , 400),
    UTENTEALREADYPAID("UtenteAlreadyPaid", "Utente ha già effettuato il pagamento!", 400),
    PAYMENTFAILED("PaymentFailed", "Pagamento non andato a buon fine!", 400);

    private String code;
    private String message;
    private int httpStatus;
}
