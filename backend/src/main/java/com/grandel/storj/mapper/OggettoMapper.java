package com.grandel.storj.mapper;

import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.service.StoriaBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Oggetto;

@Mapper(componentModel = "spring")
public abstract class OggettoMapper {

    @Autowired
    StoriaMapper storiaMapper;
    @Autowired
    StoriaBL storiaBL;

    public abstract OggettoDTO oggettoToOggettoDTO(Oggetto oggetto);

    public abstract OggettoDTO oggettoEntityToOggettoDTO(OggettoEntity oggettoEntity);

    public abstract Oggetto oggettoDTOToOggetto(OggettoDTO oggettoDTO);

    public abstract OggettoEntity oggettoDTOToOggettoEntity(OggettoDTO oggettoDTO);

    public StoriaEntity map(Long id) {
        StoriaDTO storiaDTO = storiaBL.getStoriaDTOById(id);
        return storiaMapper.storiaDTOToStoriaEntity(storiaDTO);
    }

    public Long map(StoriaEntity storiaEntity) {
        return storiaEntity.getId();
    }
}
