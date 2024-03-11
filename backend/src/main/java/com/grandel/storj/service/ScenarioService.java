package com.grandel.storj.service;

import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.repository.ScenarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ScenarioService {
    @Autowired
    private ScenarioRepository scenarioRepository;

    public ScenarioEntity postScenario(ScenarioEntity scenarioEntity){
        return scenarioRepository.save(scenarioEntity);
    }

    public Optional<ScenarioEntity> findById(Long id){
        return scenarioRepository.findById(id);
    }

    public ScenarioEntity putScenario(ScenarioEntity scenarioEntity){
        return scenarioRepository.save(scenarioEntity);
    }

    public void deleteScenario(Long id){
        scenarioRepository.deleteById(id);
    }
}
