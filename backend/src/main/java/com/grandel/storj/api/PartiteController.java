package com.grandel.storj.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.PartiteApi;
import storj.model.Partita;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class PartiteController implements PartiteApi {


    public ResponseEntity<Partita> getPartitaById(Long idPartita) {
        return PartiteApi.super.getPartitaById(idPartita);
    }

    public ResponseEntity<Partita> postPartita(Partita partita) {
        return PartiteApi.super.postPartita(partita);
    }

    public ResponseEntity<Partita> putPartita(Long idPartita, Partita partita) {
        return PartiteApi.super.putPartita(idPartita, partita);
    }

    public ResponseEntity<Void> deletePartita(Long idPartita) {
        return PartiteApi.super.deletePartita(idPartita);
    }
}
