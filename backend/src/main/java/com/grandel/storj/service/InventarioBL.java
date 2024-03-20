package com.grandel.storj.service;

import com.grandel.storj.dto.InventarioDTO;
import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.entity.InventarioEntity;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.InventarioMapper;
import com.grandel.storj.mapper.OggettoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InventarioBL {

    @Autowired
    private InventarioService inventarioService;
    @Autowired
    private InventarioMapper inventarioMapper;
    @Autowired
    private OggettoMapper oggettoMapper;

    public InventarioDTO getInventarioDTOById(Long id) {
        Optional<InventarioEntity> inventario = inventarioService.findById(id);
        if (!inventario.isPresent()) {
            throw new ErrorException(ErrorEnum.INVENTARIONOTFOUND);
        }

        return inventarioMapper.inventarioEntityToInventarioDTO(inventario.get());
    }

    public InventarioDTO postInventario(InventarioDTO inventarioDTO) {
        InventarioEntity inventarioEntity = inventarioMapper.inventarioDTOTOInventarioEntity(inventarioDTO);
        inventarioEntity = inventarioService.postInventario(inventarioEntity);
        return inventarioMapper.inventarioEntityToInventarioDTO(inventarioEntity);
    }

    public InventarioDTO putInventario(Long id, InventarioDTO inventarioDTO) {
        Optional<InventarioEntity> inventario = inventarioService.findById(id);
        if (!inventario.isPresent()) {
            throw new ErrorException(ErrorEnum.INVENTARIONOTFOUND);
        }

        inventarioDTO.setId(id);
        InventarioEntity inventarioEntity = inventarioMapper.inventarioDTOTOInventarioEntity(inventarioDTO);
        inventarioEntity = inventarioService.putInventario(inventarioEntity);

        return inventarioMapper.inventarioEntityToInventarioDTO(inventarioEntity);
    }

    public void deleteInventario(Long id) {
        if (getInventarioDTOById(id) != null) {
            inventarioService.deleteInventario(id);
        }
    }

    public List<OggettoDTO> getOggettiByPartita(Long idPartita) {
        List<OggettoDTO> oggetti = new ArrayList<>();

        for (OggettoEntity x : inventarioService.getOggettiByPartita(idPartita)) {
            oggetti.add(oggettoMapper.oggettoEntityToOggettoDTO(x));
        }

        return oggetti;
    }
}
