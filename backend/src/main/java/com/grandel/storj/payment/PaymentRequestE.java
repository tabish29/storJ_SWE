package com.grandel.storj.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequestE {
    Double amount;
    String cardHolder;
    String cardNumber;
    String cvv;
}
