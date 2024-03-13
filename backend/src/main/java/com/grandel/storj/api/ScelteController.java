package com.grandel.storj.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.ScelteApi;
import storj.model.Indovinello;
import storj.model.Multipla;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class ScelteController implements ScelteApi {


    public ResponseEntity<Indovinello> getSceltaIndovinelloById(Long idScelta) {
        return ScelteApi.super.getSceltaIndovinelloById(idScelta);
    }

    public ResponseEntity<Multipla> getSceltaMultiplaById(Long idScelta) {
        return ScelteApi.super.getSceltaMultiplaById(idScelta);
    }

    public ResponseEntity<Indovinello> postSceltaIndovinello(Indovinello indovinello) {
        return ScelteApi.super.postSceltaIndovinello(indovinello);
    }

    public ResponseEntity<Multipla> postSceltaMultipla(Multipla multipla) {
        return ScelteApi.super.postSceltaMultipla(multipla);
    }

    public ResponseEntity<Indovinello> putSceltaIndovinello(Long idScelta, Indovinello indovinello) {
        return ScelteApi.super.putSceltaIndovinello(idScelta, indovinello);
    }

    public ResponseEntity<Multipla> putSceltaMultipla(Long idScelta, Multipla multipla) {
        return ScelteApi.super.putSceltaMultipla(idScelta, multipla);
    }

    public ResponseEntity<Void> deleteSceltaIndovinello(Long idScelta) {
        return ScelteApi.super.deleteSceltaIndovinello(idScelta);
    }

    public ResponseEntity<Void> deleteSceltaMultipla(Long idScelta) {
        return ScelteApi.super.deleteSceltaMultipla(idScelta);
    }
}
