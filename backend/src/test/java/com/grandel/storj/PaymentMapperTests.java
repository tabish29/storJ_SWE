package com.grandel.storj;

import com.grandel.storj.mapper.PaymentRequestMapper;
import com.grandel.storj.payment.PaymentRequestDTO;
import com.grandel.storj.payment.PaymentRequestE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.PaymentRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentMapperTests extends AbstractTests {

    @Autowired
    private PaymentRequestMapper paymentRequestMapper;

    @Test
    void testMapperPaymentRequest() {
        PaymentRequest paymentRequest = new PaymentRequest();
        paymentRequest.setAmount(1D);
        paymentRequest.setCardHolder("1");
        paymentRequest.setCardNumber("1");
        paymentRequest.setCvv("1");

        PaymentRequestDTO paymentRequestDTO = paymentRequestMapper.paymentRequestToPaymentRequestDTO(paymentRequest);

        //Testing Model -> DTO
        assertEquals(paymentRequest.getAmount(), paymentRequestDTO.getAmount());
        assertEquals(paymentRequest.getCardHolder(), paymentRequestDTO.getCardHolder());
        assertEquals(paymentRequest.getCardNumber(), paymentRequestDTO.getCardNumber());
        assertEquals(paymentRequest.getCvv(), paymentRequestDTO.getCvv());

        PaymentRequest paymentRequest1 = paymentRequestMapper.paymentRequestDTOToPaymentRequest(paymentRequestDTO);

        //Testing DTO -> Model
        assertEquals(paymentRequestDTO.getAmount(), paymentRequest1.getAmount());
        assertEquals(paymentRequestDTO.getCardHolder(), paymentRequest1.getCardHolder());
        assertEquals(paymentRequestDTO.getCardNumber(), paymentRequest1.getCardNumber());
        assertEquals(paymentRequestDTO.getCvv(), paymentRequest1.getCvv());

        //Testing Model -> Model
        assertEquals(paymentRequest.getAmount(), paymentRequest1.getAmount());
        assertEquals(paymentRequest.getCardHolder(), paymentRequest1.getCardHolder());
        assertEquals(paymentRequest.getCardNumber(), paymentRequest1.getCardNumber());
        assertEquals(paymentRequest.getCvv(), paymentRequest1.getCvv());
    }

    @Test
    void testMapperPaymentRequestE() {
        PaymentRequestE paymentRequest = new PaymentRequestE(1D, "1", "1", "1");

        PaymentRequestDTO paymentRequestDTO = paymentRequestMapper.paymentRequestEToPaymentRequestDTO(paymentRequest);

        //Testing Entity -> DTO
        assertEquals(paymentRequest.getAmount(), paymentRequestDTO.getAmount());
        assertEquals(paymentRequest.getCardHolder(), paymentRequestDTO.getCardHolder());
        assertEquals(paymentRequest.getCardNumber(), paymentRequestDTO.getCardNumber());
        assertEquals(paymentRequest.getCvv(), paymentRequestDTO.getCvv());

        PaymentRequestE paymentRequest1 = paymentRequestMapper.paymentRequestDTOToPaymentRequestE(paymentRequestDTO);

        //Testing DTO -> Entity
        assertEquals(paymentRequestDTO.getAmount(), paymentRequest1.getAmount());
        assertEquals(paymentRequestDTO.getCardHolder(), paymentRequest1.getCardHolder());
        assertEquals(paymentRequestDTO.getCardNumber(), paymentRequest1.getCardNumber());
        assertEquals(paymentRequestDTO.getCvv(), paymentRequest1.getCvv());

        //Testing Entity -> Entity
        assertEquals(paymentRequest.getAmount(), paymentRequest1.getAmount());
        assertEquals(paymentRequest.getCardHolder(), paymentRequest1.getCardHolder());
        assertEquals(paymentRequest.getCardNumber(), paymentRequest1.getCardNumber());
        assertEquals(paymentRequest.getCvv(), paymentRequest1.getCvv());
    }

    @Test
    void testMapperPaymentRequestNull() {
        PaymentRequest paymentRequest = null;

        PaymentRequestDTO paymentRequestDTO = paymentRequestMapper.paymentRequestToPaymentRequestDTO(paymentRequest);

        assertNull(paymentRequestDTO);

        paymentRequest = paymentRequestMapper.paymentRequestDTOToPaymentRequest(paymentRequestDTO);

        assertNull(paymentRequest);
    }

    @Test
    void testMapperPaymentRequestENull() {
        PaymentRequestE paymentRequest = null;

        PaymentRequestDTO paymentRequestDTO = paymentRequestMapper.paymentRequestEToPaymentRequestDTO(paymentRequest);

        assertNull(paymentRequestDTO);

        paymentRequest = paymentRequestMapper.paymentRequestDTOToPaymentRequestE(paymentRequestDTO);

        assertNull(paymentRequest);
    }
}
