package com.grandel.storj.api;

import com.grandel.storj.dto.PartitaDTO;
import com.grandel.storj.mapper.PartitaMapper;
import com.grandel.storj.service.PartitaBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private PartitaMapper partitaMapper;
    @Autowired
    private PartitaBL partitaBL;

    public ResponseEntity<Partita> getPartitaById(Long idPartita) {
        log.info("method getPartitaById()");

        PartitaDTO partitaDTO = partitaBL.getPartitaDTOById(idPartita);

        return new ResponseEntity<>(partitaMapper.partitaDTOToPartita(partitaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Partita> postPartita(Partita partita) {
        log.info("method postPartita()");

        PartitaDTO partitaDTO = partitaBL.postPartita(partitaMapper.partitaToPartitaDTO(partita));

        return new ResponseEntity<>(partitaMapper.partitaDTOToPartita(partitaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Partita> putPartita(Long idPartita, Partita partita) {
        log.info("method putPartita()");

        PartitaDTO partitaDTO = partitaBL.putPartita(idPartita, partitaMapper.partitaToPartitaDTO(partita));

        return new ResponseEntity<>(partitaMapper.partitaDTOToPartita(partitaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deletePartita(Long idPartita) {
        log.info("method deletePartita()");

        partitaBL.deletePartita(idPartita);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
