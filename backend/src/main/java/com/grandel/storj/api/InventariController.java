package com.grandel.storj.api;

import com.grandel.storj.dto.InventarioDTO;
import com.grandel.storj.mapper.InventarioMapper;
import com.grandel.storj.service.InventarioBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.InventariApi;
import storj.model.Inventario;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class InventariController implements InventariApi {

    @Autowired
    private InventarioBL inventarioBL;
    @Autowired
    private InventarioMapper inventarioMapper;

    public ResponseEntity<Inventario> getInventarioById(Long idInventario) {
        log.info("method getInventarioById()");

        InventarioDTO inventarioDTO = inventarioBL.getInventarioDTOById(idInventario);

        return new ResponseEntity<>(inventarioMapper.inventarioDTOToInventario(inventarioDTO), HttpStatus.OK);
    }

    public ResponseEntity<Inventario> postInventario(Inventario inventario) {
        log.info("method postInventario()");

        InventarioDTO inventarioDTO = inventarioBL.postInventario(inventarioMapper.inventarioToInventarioDTO(inventario));

        return new ResponseEntity<>(inventarioMapper.inventarioDTOToInventario(inventarioDTO), HttpStatus.OK);
    }

    public ResponseEntity<Inventario> putInventario(Long idInventario, Inventario inventario) {
        log.info("method putInventario()");

        InventarioDTO inventarioDTO = inventarioBL.putInventario(idInventario, inventarioMapper.inventarioToInventarioDTO(inventario));

        return new ResponseEntity<>(inventarioMapper.inventarioDTOToInventario(inventarioDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteInventario(Long idInventario) {
        log.info("method deleteInventario()");

        inventarioBL.deleteInventario(idInventario);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
