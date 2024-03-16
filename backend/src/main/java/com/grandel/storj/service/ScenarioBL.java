package com.grandel.storj.service;

import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.ScenarioMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ScenarioBL {

    @Autowired
    private ScenarioService scenarioService;
    @Autowired
    private ScenarioMapper scenarioMapper;

    public ScenarioDTO getScenarioDTOById(Long id) {
        Optional<ScenarioEntity> scenario = scenarioService.findById(id);
        if (!scenario.isPresent()) {
            throw new ErrorException(ErrorEnum.SCENARIONOTFOUND);
        }

        return scenarioMapper.scenarioEntityToScenarioDTO(scenario.get());
    }

    public ScenarioDTO postScenario(ScenarioDTO scenarioDTO) {
        ScenarioEntity scenarioEntity = scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);
        scenarioEntity = scenarioService.postScenario(scenarioEntity);
        return scenarioMapper.scenarioEntityToScenarioDTO(scenarioEntity);
    }

    public ScenarioDTO putScenario(Long id, ScenarioDTO scenarioDTO) {
        Optional<ScenarioEntity> scenario = scenarioService.findById(id);
        if (!scenario.isPresent()) {
            throw new ErrorException(ErrorEnum.SCENARIONOTFOUND);
        }

        scenarioDTO.setId(id);
        ScenarioEntity scenarioEntity = scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);
        scenarioEntity = scenarioService.putScenario(scenarioEntity);

        return scenarioMapper.scenarioEntityToScenarioDTO(scenarioEntity);
    }

    public void deleteScenario(Long id) {
        if (getScenarioDTOById(id) != null) {
            scenarioService.deleteScenario(id);
        }
    }

    public List<ScenarioDTO> getScenariByStoria(Long idStoria, String tipologia) {
        List<ScenarioDTO> scenari = new ArrayList<>();

        if (tipologia != null) {
            for (ScenarioEntity x : scenarioService.getScenariByTipologia(idStoria, tipologia)) {
                scenari.add(scenarioMapper.scenarioEntityToScenarioDTO(x));
            }
        } else {
            for (ScenarioEntity x : scenarioService.getScenariByStoria(idStoria)) {
                scenari.add(scenarioMapper.scenarioEntityToScenarioDTO(x));
            }
        }

        return scenari;
    }
}
