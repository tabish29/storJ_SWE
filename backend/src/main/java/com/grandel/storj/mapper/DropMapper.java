package com.grandel.storj.mapper;

import com.grandel.storj.dto.DropDTO;
import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.entity.DropEntity;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.service.OggettoBL;
import com.grandel.storj.service.ScenarioBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Drop;

@Mapper(componentModel = "spring")
public abstract class DropMapper {
    @Autowired
    ScenarioMapper scenarioMapper;
    @Autowired
    ScenarioBL scenarioBL;
    @Autowired
    OggettoMapper oggettoMapper;
    @Autowired
    OggettoBL oggettoBL;

    public abstract DropDTO dropToDropDTO(Drop drop);
    public abstract DropDTO dropEntityToDropDTO(DropEntity dropEntity);
    public abstract Drop dropDTOToDrop(DropDTO dropDTO);
    public abstract DropEntity dropDTOToDropEntity(DropDTO dropDTO);

    public ScenarioEntity mapS(Long id){
        ScenarioDTO scenarioDTO = scenarioBL.getScenarioDTOById(id);
        return scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);
    }

    public Long mapS(ScenarioEntity scenarioEntity){
        return scenarioEntity.getId();
    }

    public OggettoEntity mapO(Long id){
        OggettoDTO oggettoDTO = oggettoBL.getOggettoDTOById(id);
        return oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);
    }

    public Long mapO(OggettoEntity oggettoEntity){
        return oggettoEntity.getId();
    }
}
