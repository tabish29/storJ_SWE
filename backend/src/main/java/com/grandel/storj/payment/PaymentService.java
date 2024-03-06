package com.grandel.storj.payment;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final RestTemplate restTemplate;

    public PaymentService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public boolean makePayment(PaymentRequestE paymentRequestE) {
        String url = "http://paymentservice:6789/pay"; //URL DOCKER
        //String url = "http://0.0.0.0:6789/pay"; //URL CODING

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<PaymentRequestE> requestEntity = new HttpEntity<>(paymentRequestE, headers);

        ResponseEntity<PaymentResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, PaymentResponse.class);

        return responseEntity.getBody().getStatus().equals("Autorizzato");
    }
}
