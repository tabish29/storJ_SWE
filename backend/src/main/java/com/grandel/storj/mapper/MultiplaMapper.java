package com.grandel.storj.mapper;

import com.grandel.storj.dto.MultiplaDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.entity.MultiplaEntity;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.service.ScenarioBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Multipla;

@Mapper(componentModel = "spring")
public abstract class MultiplaMapper {

    @Autowired
    ScenarioMapper scenarioMapper;
    @Autowired
    ScenarioBL scenarioBL;

    public abstract MultiplaDTO multiplaToMultiplaDTO(Multipla multipla);

    public abstract MultiplaDTO multiplaEntityToMultiplaDTO(MultiplaEntity multiplaEntity);

    public abstract Multipla multiplaDTOToMultipla(MultiplaDTO multiplaDTO);

    public abstract MultiplaEntity multiplaDTOToMultiplaEntity(MultiplaDTO multiplaDTO);

    public ScenarioEntity mapS(Long id) {
        ScenarioDTO scenarioDTO = scenarioBL.getScenarioDTOById(id);
        return scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);
    }

    public Long mapS(ScenarioEntity scenarioEntity) {
        return scenarioEntity.getId();
    }
}
