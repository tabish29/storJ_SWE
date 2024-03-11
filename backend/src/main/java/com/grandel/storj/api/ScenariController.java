package com.grandel.storj.api;

import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.mapper.ScenarioMapper;
import com.grandel.storj.service.ScenarioBL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import storj.api.ScenariApi;
import storj.model.Scelta;
import storj.model.Scenario;

import java.util.List;

@Slf4j
@Controller
@EnableAutoConfiguration
@RequestMapping("/api/v1")
public class ScenariController implements ScenariApi {
    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private ScenarioMapper scenarioMapper;

    public ResponseEntity<Scenario> getScenarioById(Long idScenario) {
        log.info("method getScenarioById()");

        ScenarioDTO scenarioDTO = scenarioBL.getScenarioDTOById(idScenario);

        return new ResponseEntity<>(scenarioMapper.scenarioDTOToScenario(scenarioDTO), HttpStatus.OK);
    }

    public ResponseEntity<Scenario> postScenario(Scenario scenario) {
        log.info("method postScenario()");

        ScenarioDTO scenarioDTO = scenarioBL.postScenario(scenarioMapper.scenarioToScenarioDTO(scenario));

        return new ResponseEntity<>(scenarioMapper.scenarioDTOToScenario(scenarioDTO), HttpStatus.OK);
    }

    public ResponseEntity<Scenario> putScenario(Long idScenario, Scenario scenario) {
        log.info("method putScenario()");

        ScenarioDTO scenarioDTO = scenarioBL.putScenario(idScenario, scenarioMapper.scenarioToScenarioDTO(scenario));

        return new ResponseEntity<>(scenarioMapper.scenarioDTOToScenario(scenarioDTO), HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteScenario(Long idScenario) {
        log.info("method deleteScenario()");

        scenarioBL.deleteScenario(idScenario);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<List<Scelta>> getScelteByScenario(Long idScenario) {
        log.info("method getScelteByScenario()");

        //TO-DO

        return ScenariApi.super.getScelteByScenario(idScenario);
    }
}
