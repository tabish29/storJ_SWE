package com.grandel.storj.api;

import com.grandel.storj.dto.DropDTO;
import com.grandel.storj.mapper.DropMapper;
import com.grandel.storj.service.DropBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.DropApi;
import storj.model.Drop;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class DropController implements DropApi {

    @Autowired
    private DropBL dropBL;
    @Autowired
    private DropMapper dropMapper;

    public ResponseEntity<Drop> getDropById(Long idDrop) {
        log.info("method getDropById()");

        DropDTO dropDTO = dropBL.getDropDTOById(idDrop);

        return new ResponseEntity<>(dropMapper.dropDTOToDrop(dropDTO), HttpStatus.OK);
    }

    public ResponseEntity<Drop> postDrop(Drop drop) {
        log.info("method postDrop()");

        DropDTO dropDTO = dropBL.postDrop(dropMapper.dropToDropDTO(drop));

        return new ResponseEntity<>(dropMapper.dropDTOToDrop(dropDTO), HttpStatus.OK);
    }

    public ResponseEntity<Drop> putDrop(Long idDrop, Drop drop) {
        log.info("method putDrop()");

        DropDTO dropDTO = dropBL.putDrop(idDrop, dropMapper.dropToDropDTO(drop));

        return new ResponseEntity<>(dropMapper.dropDTOToDrop(dropDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteDrop(Long idDrop) {
        log.info("method deleteDrop()");

        dropBL.deleteDrop(idDrop);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
