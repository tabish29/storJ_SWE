package com.grandel.storj.mapper;

import com.grandel.storj.payment.PaymentRequestDTO;
import com.grandel.storj.payment.PaymentRequestE;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentRequestMapper {
    PaymentRequestDTO paymentRequestEToPaymentRequestDTO(PaymentRequestE paymentRequestE);
    PaymentRequestE paymentRequestDTOToPaymentRequestE(PaymentRequestDTO paymentRequestDTO);
    PaymentRequestDTO paymentRequestToPaymentRequestDTO(storj.model.PaymentRequest paymentRequest);
    storj.model.PaymentRequest paymentRequestDTOToPaymentRequest(PaymentRequestDTO paymentRequestDTO);
}
