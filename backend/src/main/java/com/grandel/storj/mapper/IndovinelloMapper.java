package com.grandel.storj.mapper;

import com.grandel.storj.dto.IndovinelloDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.entity.IndovinelloEntity;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.service.ScenarioBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Indovinello;

@Mapper(componentModel = "spring")
public abstract class IndovinelloMapper {
    @Autowired
    ScenarioMapper scenarioMapper;
    @Autowired
    ScenarioBL scenarioBL;

    public abstract IndovinelloDTO indovinelloToIndovinelloDTO(Indovinello indovinello);
    public abstract IndovinelloDTO indovinelloEntityToIndovinelloDTO(IndovinelloEntity indovinelloEntity);
    public abstract Indovinello indovinelloDTOToIndovinello(IndovinelloDTO indovinelloDTO);
    public abstract IndovinelloEntity indovinelloDTOToIndovinelloEntity(IndovinelloDTO indovinelloDTO);

    public ScenarioEntity mapS(Long id){
        ScenarioDTO scenarioDTO = scenarioBL.getScenarioDTOById(id);
        return scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);
    }

    public Long mapS(ScenarioEntity scenarioEntity){
        return scenarioEntity.getId();
    }
}
