package com.grandel.storj.api;

import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.mapper.StoriaMapper;
import com.grandel.storj.service.StoriaBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.StorieApi;
import storj.model.Oggetto;
import storj.model.Scenario;
import storj.model.Storia;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class StorieController implements StorieApi {
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private StoriaMapper storiaMapper;

    public ResponseEntity<Storia> getStoriaById(Long idStoria) {
        log.info("method getStoriaById()");

        StoriaDTO storiaDTO = storiaBL.getStoriaDTOById(idStoria);

        return new ResponseEntity<>(storiaMapper.storiaDTOToStoria(storiaDTO), HttpStatus.OK);
    }

    public ResponseEntity<List<Storia>> getStorie(String autore, String categoria) {
        log.info("method getStorie()");

        List<Storia> storie = new ArrayList<>();

        for (StoriaDTO x : storiaBL.getStorie(autore, categoria)) {
            storie.add(storiaMapper.storiaDTOToStoria(x));
        }

        return new ResponseEntity<>(storie, HttpStatus.OK);
    }

    public ResponseEntity<Storia> postStoria(Storia storia) {
        log.info("method postStoria()");

        StoriaDTO storiaDTO = storiaBL.postStoria(storiaMapper.storiaToStoriaDTO(storia));

        return new ResponseEntity<>(storiaMapper.storiaDTOToStoria(storiaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Storia> putStoria(Long idStoria, Storia storia) {
        log.info("method putStoria()");

        StoriaDTO storiaDTO = storiaBL.putStoria(idStoria, storiaMapper.storiaToStoriaDTO(storia));

        return new ResponseEntity<>(storiaMapper.storiaDTOToStoria(storiaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteStoria(Long idStoria) {
        log.info("method deleteStoria()");

        storiaBL.deleteStoria(idStoria);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Oggetto>> getOggettiByStoria(Long idStoria) {
        log.info("method getOggettiByStoria()");

        //TO-DO

        return StorieApi.super.getOggettiByStoria(idStoria);
    }

    public ResponseEntity<List<Scenario>> getScenariByStoria(Long idStoria) {
        log.info("method getScenariByStoria()");

        //TO-DO

        return StorieApi.super.getScenariByStoria(idStoria);
    }
}
