package com.grandel.storj.mapper;

import com.grandel.storj.dto.MultiplaDTO;
import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.dto.RequiredDTO;
import com.grandel.storj.entity.MultiplaEntity;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.RequiredEntity;
import com.grandel.storj.service.MultiplaBL;
import com.grandel.storj.service.OggettoBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Required;

@Mapper(componentModel = "spring")
public abstract class RequiredMapper {
    @Autowired
    MultiplaMapper multiplaMapper;
    @Autowired
    MultiplaBL multiplaBL;
    @Autowired
    OggettoMapper oggettoMapper;
    @Autowired
    OggettoBL oggettoBL;

    public abstract RequiredDTO requiredToRequiredDTO(Required required);
    public abstract RequiredDTO requiredEntityToRequiredDTO(RequiredEntity requiredEntity);
    public abstract Required requiredDTOToRequired(RequiredDTO requiredDTO);
    public abstract RequiredEntity requiredDTOToRequiredEntity(RequiredDTO requiredDTO);

    public MultiplaEntity mapM(Long id){
        MultiplaDTO multiplaDTO = multiplaBL.getMultiplaDTOById(id);
        return multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO);
    }

    public Long mapM(MultiplaEntity multiplaEntity){
        return multiplaEntity.getId();
    }

    public OggettoEntity mapO(Long id){
        OggettoDTO oggettoDTO = oggettoBL.getOggettoDTOById(id);
        return oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);
    }

    public Long mapO(OggettoEntity oggettoEntity){
        return oggettoEntity.getId();
    }
}
