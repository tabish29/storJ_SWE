package com.grandel.storj.mapper;

import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.service.OggettoBL;
import com.grandel.storj.service.StoriaBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Scenario;

@Mapper(componentModel = "spring")
public abstract class ScenarioMapper {
    @Autowired
    StoriaMapper storiaMapper;
    @Autowired
    StoriaBL storiaBL;
    @Autowired
    OggettoMapper oggettoMapper;
    @Autowired
    OggettoBL oggettoBL;

    public abstract ScenarioDTO scenarioEntityToScenarioDTO(ScenarioEntity scenarioEntity);
    public abstract ScenarioDTO scenarioToScenarioDTO(Scenario scenario);
    public abstract Scenario scenarioDTOToScenario(ScenarioDTO scenarioDTO);
    public abstract ScenarioEntity scenarioDTOToScenarioEntity(ScenarioDTO scenarioDTO);

    public StoriaEntity mapS(Long id){
        StoriaDTO storiaDTO = storiaBL.getStoriaDTOById(id);
        return storiaMapper.storiaDTOToStoriaEntity(storiaDTO);
    }

    public Long mapS(StoriaEntity storiaEntity){
        return storiaEntity.getId();
    }

    public OggettoEntity mapO(Long id){
        if(id == 0){
            OggettoEntity oggettoEntity = new OggettoEntity();
            oggettoEntity.setId(0L);
            return oggettoEntity;
        }

        OggettoDTO oggettoDTO = oggettoBL.getOggettoDTOById(id);
        return oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);
    }

    public Long mapO(OggettoEntity oggettoEntity){
        return oggettoEntity.getId();
    }
}
