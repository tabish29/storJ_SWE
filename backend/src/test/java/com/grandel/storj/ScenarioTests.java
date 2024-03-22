package com.grandel.storj;

import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.ScenarioBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Scenario;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class ScenarioTests extends AbstractTests {

    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private UtenteBL utenteBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetScenarioDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);

        ScenarioDTO scenarioDTOcreated = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTOreturned = scenarioBL.getScenarioDTOById(scenarioDTOcreated.getId());

        assertEquals(scenarioDTO.getIdStoria().getId(), scenarioDTOreturned.getIdStoria().getId());
        assertEquals(scenarioDTO.getTesto(), scenarioDTOreturned.getTesto());
        assertEquals(scenarioDTO.getTipoRisposta().toString(), scenarioDTOreturned.getTipoRisposta().toString());
        assertEquals(scenarioDTO.getTipoScenario().toString(), scenarioDTOreturned.getTipoScenario().toString());
    }

    @Test
    void testGetScenarioDTOByIdFail() {
        try {
            scenarioBL.getScenarioDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "ScenarioNotFound - Scenario non trovato!");
        }
    }

    @Test
    void testPostScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);

        ScenarioDTO scenarioDTOcreated = scenarioBL.postScenario(scenarioDTO);

        assertEquals(scenarioDTO.getIdStoria().getId(), scenarioDTOcreated.getIdStoria().getId());
        assertEquals(scenarioDTO.getTesto(), scenarioDTOcreated.getTesto());
        assertEquals(scenarioDTO.getTipoRisposta().toString(), scenarioDTOcreated.getTipoRisposta().toString());
        assertEquals(scenarioDTO.getTipoScenario().toString(), scenarioDTOcreated.getTipoScenario().toString());
    }

    @Test
    void testPutScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);

        ScenarioDTO scenarioDTOcreated = scenarioBL.postScenario(scenarioDTO);

        scenarioDTO.setTesto("testo cambiato");

        ScenarioDTO scenarioDTOput = scenarioBL.putScenario(scenarioDTOcreated.getId(), scenarioDTO);

        assertEquals(scenarioDTO.getIdStoria().getId(), scenarioDTOput.getIdStoria().getId());
        assertEquals(scenarioDTO.getTesto(), scenarioDTOput.getTesto());
        assertEquals(scenarioDTO.getTipoRisposta().toString(), scenarioDTOput.getTipoRisposta().toString());
        assertEquals(scenarioDTO.getTipoScenario().toString(), scenarioDTOput.getTipoScenario().toString());
    }

    @Test
    void testPutScenarioFail() {
        try {
            scenarioBL.putScenario(1L, new ScenarioDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "ScenarioNotFound - Scenario non trovato!");
        }
    }

    @Test
    void testDeleteScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);

        ScenarioDTO scenarioDTOcreated = scenarioBL.postScenario(scenarioDTO);

        scenarioBL.getScenarioDTOById(scenarioDTOcreated.getId());

        scenarioBL.deleteScenario(scenarioDTOcreated.getId());

        try {
            scenarioBL.getScenarioDTOById(scenarioDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "ScenarioNotFound - Scenario non trovato!");
        }
    }

    @Test
    void testGetScenariByStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);

        scenarioBL.postScenario(scenarioDTO);
        scenarioBL.postScenario(scenarioDTO);

        assertEquals(2, scenarioBL.getScenariByStoria(storiaDTO.getId(), null).size());
        assertEquals(2, scenarioBL.getScenariByStoria(storiaDTO.getId(), "NORMALE").size());
        assertEquals(0, scenarioBL.getScenariByStoria(storiaDTO.getId(), "INIZIALE").size());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        Scenario scenario = creaScenarioModel(1L, storiaDTO.getId(), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);

        ScenarioDTO scenarioDTO = scenarioMapper.scenarioToScenarioDTO(scenario);

        //Testing Model -> DTO
        assertEquals(scenario.getIdStoria(), scenarioDTO.getIdStoria().getId());
        assertEquals(scenario.getTesto(), scenarioDTO.getTesto());
        assertEquals(scenario.getTipoRisposta().toString(), scenarioDTO.getTipoRisposta().toString());
        assertEquals(scenario.getTipoScenario().toString(), scenarioDTO.getTipoScenario().toString());

        Scenario scenario1 = scenarioMapper.scenarioDTOToScenario(scenarioDTO);

        //Testing DTO -> Model
        assertEquals(scenarioDTO.getIdStoria().getId(), scenario1.getIdStoria());
        assertEquals(scenarioDTO.getTesto(), scenario1.getTesto());
        assertEquals(scenarioDTO.getTipoRisposta().toString(), scenario1.getTipoRisposta().toString());
        assertEquals(scenarioDTO.getTipoScenario().toString(), scenario1.getTipoScenario().toString());

        //Testing Model -> Model
        assertEquals(scenario.getIdStoria(), scenario1.getIdStoria());
        assertEquals(scenario.getTesto(), scenario1.getTesto());
        assertEquals(scenario.getTipoRisposta().toString(), scenario1.getTipoRisposta().toString());
        assertEquals(scenario.getTipoScenario().toString(), scenario1.getTipoScenario().toString());
    }

    @Test
    void testMapperScenarioEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioEntity scenario = creaScenarioEntity(1L, storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", ScenarioEntity.TipoRispostaEnum.MULTIPLA, ScenarioEntity.TipoScenarioEnum.NORMALE);

        ScenarioDTO scenarioDTO = scenarioMapper.scenarioEntityToScenarioDTO(scenario);

        //Testing Entity -> DTO
        assertEquals(scenario.getIdStoria().getId(), scenarioDTO.getIdStoria().getId());
        assertEquals(scenario.getTesto(), scenarioDTO.getTesto());
        assertEquals(scenario.getTipoRisposta().toString(), scenarioDTO.getTipoRisposta().toString());
        assertEquals(scenario.getTipoScenario().toString(), scenarioDTO.getTipoScenario().toString());

        ScenarioEntity scenario1 = scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);

        //Testing DTO -> Entity
        assertEquals(scenarioDTO.getIdStoria().getId(), scenario1.getIdStoria().getId());
        assertEquals(scenarioDTO.getTesto(), scenario1.getTesto());
        assertEquals(scenarioDTO.getTipoRisposta().toString(), scenario1.getTipoRisposta().toString());
        assertEquals(scenarioDTO.getTipoScenario().toString(), scenario1.getTipoScenario().toString());

        //Testing Entity -> Entity
        assertEquals(scenario.getIdStoria().getId(), scenario1.getIdStoria().getId());
        assertEquals(scenario.getTesto(), scenario1.getTesto());
        assertEquals(scenario.getTipoRisposta().toString(), scenario1.getTipoRisposta().toString());
        assertEquals(scenario.getTipoScenario().toString(), scenario1.getTipoScenario().toString());
    }

    @Test
    void testMapperScenarioNull() {
        Scenario scenario = null;

        ScenarioDTO scenarioDTO = scenarioMapper.scenarioToScenarioDTO(scenario);

        assertNull(scenarioDTO);

        scenario = scenarioMapper.scenarioDTOToScenario(scenarioDTO);

        assertNull(scenario);
    }

    @Test
    void testMapperScenarioEntityNull() {
        ScenarioEntity scenario = null;

        ScenarioDTO scenarioDTO = scenarioMapper.scenarioEntityToScenarioDTO(scenario);

        assertNull(scenarioDTO);

        scenario = scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);

        assertNull(scenario);
    }
}
