package com.grandel.storj.api;

import com.grandel.storj.dto.IndovinelloDTO;
import com.grandel.storj.dto.MultiplaDTO;
import com.grandel.storj.dto.RequiredDTO;
import com.grandel.storj.mapper.IndovinelloMapper;
import com.grandel.storj.mapper.MultiplaMapper;
import com.grandel.storj.mapper.RequiredMapper;
import com.grandel.storj.service.IndovinelloBL;
import com.grandel.storj.service.MultiplaBL;
import com.grandel.storj.service.RequiredBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.ScelteApi;
import storj.model.Indovinello;
import storj.model.Multipla;
import storj.model.Required;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class ScelteController implements ScelteApi {
    @Autowired
    private MultiplaMapper multiplaMapper;
    @Autowired
    private MultiplaBL multiplaBL;
    @Autowired
    private IndovinelloMapper indovinelloMapper;
    @Autowired
    private IndovinelloBL indovinelloBL;
    @Autowired
    private RequiredBL requiredBL;
    @Autowired
    private RequiredMapper requiredMapper;

    public ResponseEntity<Indovinello> getSceltaIndovinelloById(Long idScelta) {
        log.info("method getSceltaIndovinelloById()");

        IndovinelloDTO indovinelloDTO = indovinelloBL.getIndovinelloDTOById(idScelta);

        return new ResponseEntity<>(indovinelloMapper.indovinelloDTOToIndovinello(indovinelloDTO), HttpStatus.OK);
    }

    public ResponseEntity<Multipla> getSceltaMultiplaById(Long idScelta) {
        log.info("method getSceltaMultiplaById()");

        MultiplaDTO multiplaDTO = multiplaBL.getMultiplaDTOById(idScelta);

        return new ResponseEntity<>(multiplaMapper.multiplaDTOToMultipla(multiplaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Indovinello> postSceltaIndovinello(Indovinello indovinello) {
        log.info("method postSceltaIndovinello()");

        IndovinelloDTO indovinelloDTO = indovinelloBL.postIndovinello(indovinelloMapper.indovinelloToIndovinelloDTO(indovinello));

        return new ResponseEntity<>(indovinelloMapper.indovinelloDTOToIndovinello(indovinelloDTO), HttpStatus.OK);
    }

    public ResponseEntity<Multipla> postSceltaMultipla(Multipla multipla) {
        log.info("method postSceltaMultipla()");

        MultiplaDTO multiplaDTO = multiplaBL.postMultipla(multiplaMapper.multiplaToMultiplaDTO(multipla));

        return new ResponseEntity<>(multiplaMapper.multiplaDTOToMultipla(multiplaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Indovinello> putSceltaIndovinello(Long idScelta, Indovinello indovinello) {
        log.info("method putSceltaIndovinello()");

        IndovinelloDTO indovinelloDTO = indovinelloBL.putIndovinello(idScelta, indovinelloMapper.indovinelloToIndovinelloDTO(indovinello));

        return new ResponseEntity<>(indovinelloMapper.indovinelloDTOToIndovinello(indovinelloDTO), HttpStatus.OK);
    }

    public ResponseEntity<Multipla> putSceltaMultipla(Long idScelta, Multipla multipla) {
        log.info("method putSceltaMultipla()");

        MultiplaDTO multiplaDTO = multiplaBL.putMultipla(idScelta, multiplaMapper.multiplaToMultiplaDTO(multipla));

        return new ResponseEntity<>(multiplaMapper.multiplaDTOToMultipla(multiplaDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSceltaIndovinello(Long idScelta) {
        log.info("method deleteSceltaIndovinello()");

        indovinelloBL.deleteIndovinello(idScelta);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteSceltaMultipla(Long idScelta) {
        log.info("method deleteSceltaMultipla()");

        multiplaBL.deleteMultipla(idScelta);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Required> getRequiredByScelta(Long idScelta) {
        log.info("method getRequiredByScelta()");

        RequiredDTO requiredDTO = requiredBL.getRequiredByScelta(idScelta);

        return new ResponseEntity<>(requiredMapper.requiredDTOToRequired(requiredDTO), HttpStatus.OK);
    }
}
