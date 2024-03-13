package com.grandel.storj.service;

import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.OggettoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OggettoBL {
    @Autowired
    private OggettoService oggettoService;
    @Autowired
    private OggettoMapper oggettoMapper;

    public OggettoDTO getOggettoDTOById(Long id){
        Optional<OggettoEntity> oggetto = oggettoService.findById(id);
        if(!oggetto.isPresent()){
            throw new ErrorException(ErrorEnum.OGGETTONOTFOUND);
        }

        return oggettoMapper.oggettoEntityToOggettoDTO(oggetto.get());
    }

    public OggettoDTO postOggetto(OggettoDTO oggettoDTO){
        OggettoEntity oggettoEntity = oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);
        oggettoEntity = oggettoService.postOggetto(oggettoEntity);
        return oggettoMapper.oggettoEntityToOggettoDTO(oggettoEntity);
    }

    public OggettoDTO putOggetto(Long id, OggettoDTO oggettoDTO){
        Optional<OggettoEntity> oggetto = oggettoService.findById(id);
        if(!oggetto.isPresent()){
            throw new ErrorException(ErrorEnum.OGGETTONOTFOUND);
        }

        oggettoDTO.setId(id);
        OggettoEntity oggettoEntity = oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);
        oggettoEntity = oggettoService.putOggetto(oggettoEntity);

        return oggettoMapper.oggettoEntityToOggettoDTO(oggettoEntity);
    }

    public void deleteOggetto(Long id){
        if(getOggettoDTOById(id) != null){
            oggettoService.deleteOggetto(id);
        }
    }

    public List<OggettoDTO> getOggettiByStoria(Long idStoria) {
        List<OggettoDTO> oggetti = new ArrayList<>();

        for (OggettoEntity x : oggettoService.getOggettiByStoria(idStoria)) {
            oggetti.add(oggettoMapper.oggettoEntityToOggettoDTO(x));
        }

        return oggetti;
    }
}
