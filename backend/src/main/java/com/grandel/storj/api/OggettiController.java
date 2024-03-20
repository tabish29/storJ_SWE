package com.grandel.storj.api;

import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.mapper.OggettoMapper;
import com.grandel.storj.service.OggettoBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.OggettiApi;
import storj.model.Oggetto;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class OggettiController implements OggettiApi {

    @Autowired
    private OggettoBL oggettoBL;
    @Autowired
    private OggettoMapper oggettoMapper;

    public ResponseEntity<Oggetto> getOggettoById(Long idOggetto) {
        log.info("method getOggettoById()");

        OggettoDTO oggettoDTO = oggettoBL.getOggettoDTOById(idOggetto);

        return new ResponseEntity<>(oggettoMapper.oggettoDTOToOggetto(oggettoDTO), HttpStatus.OK);
    }

    public ResponseEntity<Oggetto> postOggetto(Oggetto oggetto) {
        log.info("method postOggetto()");

        OggettoDTO oggettoDTO = oggettoBL.postOggetto(oggettoMapper.oggettoToOggettoDTO(oggetto));

        return new ResponseEntity<>(oggettoMapper.oggettoDTOToOggetto(oggettoDTO), HttpStatus.OK);
    }

    public ResponseEntity<Oggetto> putOggetto(Long idOggetto, Oggetto oggetto) {
        log.info("method putOggetto()");

        OggettoDTO oggettoDTO = oggettoBL.putOggetto(idOggetto, oggettoMapper.oggettoToOggettoDTO(oggetto));

        return new ResponseEntity<>(oggettoMapper.oggettoDTOToOggetto(oggettoDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteOggetto(Long idOggetto) {
        log.info("method deleteOggetto()");

        oggettoBL.deleteOggetto(idOggetto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
