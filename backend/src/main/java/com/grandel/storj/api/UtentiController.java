package com.grandel.storj.api;

import com.grandel.storj.dto.PartitaDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.mapper.PartitaMapper;
import com.grandel.storj.mapper.PaymentRequestMapper;
import com.grandel.storj.mapper.StoriaMapper;
import com.grandel.storj.mapper.UtenteMapper;
import com.grandel.storj.service.PartitaBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.UtentiApi;
import storj.model.Partita;
import storj.model.PaymentRequest;
import storj.model.Storia;
import storj.model.Utente;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class UtentiController implements UtentiApi {

    @Autowired
    private UtenteMapper utenteMapper;
    @Autowired
    private UtenteBL utenteBL;
    @Autowired
    private PaymentRequestMapper paymentRequestMapper;
    @Autowired
    private PartitaMapper partitaMapper;
    @Autowired
    private PartitaBL partitaBL;
    @Autowired
    private StoriaMapper storiaMapper;
    @Autowired
    private StoriaBL storiaBL;

    public ResponseEntity<Utente> getUtenteByUsername(String username){
        log.info("method getUtenteByUsername()");

        UtenteDTO utenteDTO = utenteBL.getUtenteDTOByUsername(username);

        return new ResponseEntity<>(utenteMapper.utenteDTOToUtente(utenteDTO), HttpStatus.OK);
    }

    public ResponseEntity<Utente> getUtenteById(Long idUtente) {
        log.info("method getUtenteById()");

        UtenteDTO utenteDTO = utenteBL.getUtenteDTObyId(idUtente);

        return new ResponseEntity<>(utenteMapper.utenteDTOToUtente(utenteDTO), HttpStatus.OK);
    }

    public ResponseEntity<Utente> postUtente(Utente utente) {
        log.info("method postUtente()");

        UtenteDTO utenteDTO = utenteBL.postUtente(utenteMapper.utenteToUtenteDTO(utente));

        return new ResponseEntity<>(utenteMapper.utenteDTOToUtente(utenteDTO), HttpStatus.OK);
    }

    public ResponseEntity<Utente> putUtente(Long idUtente, Utente utente) {
        log.info("method putUtente()");

        UtenteDTO utenteDTO = utenteBL.putUtente(idUtente, utenteMapper.utenteToUtenteDTO(utente));

        return new ResponseEntity<>(utenteMapper.utenteDTOToUtente(utenteDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteUtente(Long idUtente) {
        log.info("method deleteUtente()");

        utenteBL.deleteUtente(idUtente);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Utente> utentePayment(String username, PaymentRequest paymentRequest) {
        log.info("method userPayment()");

        UtenteDTO utenteDTO = utenteBL.utentePayment(username, paymentRequestMapper.paymentRequestToPaymentRequestDTO(paymentRequest));

        return new ResponseEntity<>(utenteMapper.utenteDTOToUtente(utenteDTO), HttpStatus.OK);
    }

    public ResponseEntity<List<Partita>> getPartiteByUtente(Long idUtente) {
        log.info("method getPartiteByUtente()");

        List<Partita> partite = new ArrayList<>();

        for (PartitaDTO x : partitaBL.getPartiteByUtente(idUtente)) {
            partite.add(partitaMapper.partitaDTOToPartita(x));
        }

        return new ResponseEntity<>(partite, HttpStatus.OK);
    }

    public ResponseEntity<List<Storia>> getStorieByUtente(Long idUtente) {
        log.info("method getStorieByUtente()");

        List<Storia> storie = new ArrayList<>();

        for (StoriaDTO x : storiaBL.getStorieByUtente(idUtente)) {
            storie.add(storiaMapper.storiaDTOToStoria(x));
        }

        return new ResponseEntity<>(storie, HttpStatus.OK);
    }
}
