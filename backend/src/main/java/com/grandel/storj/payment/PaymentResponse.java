package com.grandel.storj.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class PaymentResponse {
    String status;
    double total;
    double fee;
    String message;
}
