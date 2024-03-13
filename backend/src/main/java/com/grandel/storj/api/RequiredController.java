package com.grandel.storj.api;

import com.grandel.storj.dto.RequiredDTO;
import com.grandel.storj.mapper.RequiredMapper;
import com.grandel.storj.service.RequiredBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.RequiredApi;
import storj.model.Required;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class RequiredController implements RequiredApi {

    @Autowired
    private RequiredBL requiredBL;
    @Autowired
    private RequiredMapper requiredMapper;

    public ResponseEntity<Required> getRequiredById(Long idRequired) {
        log.info("method getRequiredById()");

        RequiredDTO requiredDTO = requiredBL.getRequiredDTOById(idRequired);

        return new ResponseEntity<>(requiredMapper.requiredDTOToRequired(requiredDTO), HttpStatus.OK);
    }

    public ResponseEntity<Required> postRequired(Required required) {
        log.info("method postRequired()");

        RequiredDTO requiredDTO = requiredBL.postRequired(requiredMapper.requiredToRequiredDTO(required));

        return new ResponseEntity<>(requiredMapper.requiredDTOToRequired(requiredDTO), HttpStatus.OK);
    }

    public ResponseEntity<Required> putRequired(Long idRequired, Required required) {
        log.info("method putRequired()");

        RequiredDTO requiredDTO = requiredBL.putRequired(idRequired, requiredMapper.requiredToRequiredDTO(required));

        return new ResponseEntity<>(requiredMapper.requiredDTOToRequired(requiredDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteRequired(Long idRequired) {
        log.info("method deleteRequired()");

        requiredBL.deleteRequired(idRequired);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
