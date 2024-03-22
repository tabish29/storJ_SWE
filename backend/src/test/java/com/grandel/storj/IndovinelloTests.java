package com.grandel.storj;

import com.grandel.storj.dto.IndovinelloDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.IndovinelloEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.IndovinelloBL;
import com.grandel.storj.service.ScenarioBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Indovinello;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class IndovinelloTests extends AbstractTests {

    @Autowired
    private IndovinelloBL indovinelloBL;
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
    void testGetIndovinelloDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));

        IndovinelloDTO indovinelloDTOcreated = indovinelloBL.postIndovinello(indovinelloDTO);

        IndovinelloDTO indovinelloDTOreturned = indovinelloBL.getIndovinelloDTOById(indovinelloDTOcreated.getId());

        assertEquals(indovinelloDTO.getIdScenario().getId(), indovinelloDTOreturned.getIdScenario().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaCorretta().getId(), indovinelloDTOreturned.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaSbagliata().getId(), indovinelloDTOreturned.getIdScenarioRispostaSbagliata().getId());
        assertEquals(indovinelloDTO.getTesto(), indovinelloDTOreturned.getTesto());
        assertEquals(indovinelloDTO.getRisposta(), indovinelloDTOreturned.getRisposta());
    }

    @Test
    void testGetIndovinelloDTOByIdFail() {
        try {
            indovinelloBL.getIndovinelloDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "IndovinelloNotFound - Indovinello non trovato!");
        }
    }

    @Test
    void testPostIndovinello() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));

        IndovinelloDTO indovinelloDTOcreated = indovinelloBL.postIndovinello(indovinelloDTO);

        assertEquals(indovinelloDTO.getIdScenario().getId(), indovinelloDTOcreated.getIdScenario().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaCorretta().getId(), indovinelloDTOcreated.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaSbagliata().getId(), indovinelloDTOcreated.getIdScenarioRispostaSbagliata().getId());
        assertEquals(indovinelloDTO.getTesto(), indovinelloDTOcreated.getTesto());
        assertEquals(indovinelloDTO.getRisposta(), indovinelloDTOcreated.getRisposta());
    }

    @Test
    void testPutIndovinello() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));

        IndovinelloDTO indovinelloDTOcreated = indovinelloBL.postIndovinello(indovinelloDTO);

        indovinelloDTO.setTesto("testo 2");

        IndovinelloDTO indovinelloDTOput = indovinelloBL.putIndovinello(indovinelloDTOcreated.getId(), indovinelloDTO);

        assertEquals(indovinelloDTO.getIdScenario().getId(), indovinelloDTOput.getIdScenario().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaCorretta().getId(), indovinelloDTOput.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaSbagliata().getId(), indovinelloDTOput.getIdScenarioRispostaSbagliata().getId());
        assertEquals(indovinelloDTO.getTesto(), indovinelloDTOput.getTesto());
        assertEquals(indovinelloDTO.getRisposta(), indovinelloDTOput.getRisposta());
    }

    @Test
    void testPutIndovinelloFail() {
        try {
            indovinelloBL.putIndovinello(1L, new IndovinelloDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "IndovinelloNotFound - Indovinello non trovato!");
        }
    }

    @Test
    void testDeleteIndovinello() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));

        IndovinelloDTO indovinelloDTOcreated = indovinelloBL.postIndovinello(indovinelloDTO);

        indovinelloBL.getIndovinelloDTOById(indovinelloDTOcreated.getId());

        indovinelloBL.deleteIndovinello(indovinelloDTOcreated.getId());

        try {
            indovinelloBL.getIndovinelloDTOById(indovinelloDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "IndovinelloNotFound - Indovinello non trovato!");
        }
    }

    @Test
    void testGetIndovinelloByScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));

        indovinelloDTO = indovinelloBL.postIndovinello(indovinelloDTO);

        assertEquals(indovinelloDTO.getIdScenario().getId(), indovinelloBL.getIndovinelloByScenario(scenarioDTO.getId()).getIdScenario().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaSbagliata().getId(), indovinelloBL.getIndovinelloByScenario(scenarioDTO.getId()).getIdScenarioRispostaSbagliata().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaCorretta().getId(), indovinelloBL.getIndovinelloByScenario(scenarioDTO.getId()).getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinelloDTO.getTesto(), indovinelloBL.getIndovinelloByScenario(scenarioDTO.getId()).getTesto());
        assertEquals(indovinelloDTO.getRisposta(), indovinelloBL.getIndovinelloByScenario(scenarioDTO.getId()).getRisposta());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperIndovinello() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        Indovinello indovinello = creaIndovinelloModel(1L, scenarioDTO.getId(), "testo", "risposta", scenarioDTO1.getId(), scenarioDTO2.getId());

        IndovinelloDTO indovinelloDTO = indovinelloMapper.indovinelloToIndovinelloDTO(indovinello);

        //Testing Model -> DTO
        assertEquals(indovinello.getTesto(), indovinelloDTO.getTesto());
        assertEquals(indovinello.getRisposta(), indovinelloDTO.getRisposta());
        assertEquals(indovinello.getIdScenario(), indovinelloDTO.getIdScenario().getId());
        assertEquals(indovinello.getIdScenarioRispostaCorretta(), indovinelloDTO.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinello.getIdScenarioRispostaSbagliata(), indovinelloDTO.getIdScenarioRispostaSbagliata().getId());

        Indovinello indovinello1 = indovinelloMapper.indovinelloDTOToIndovinello(indovinelloDTO);

        //Testing DTO -> Model
        assertEquals(indovinelloDTO.getTesto(), indovinello1.getTesto());
        assertEquals(indovinelloDTO.getRisposta(), indovinello1.getRisposta());
        assertEquals(indovinelloDTO.getIdScenario().getId(), indovinello1.getIdScenario());
        assertEquals(indovinelloDTO.getIdScenarioRispostaCorretta().getId(), indovinello1.getIdScenarioRispostaCorretta());
        assertEquals(indovinelloDTO.getIdScenarioRispostaSbagliata().getId(), indovinello1.getIdScenarioRispostaSbagliata());

        //Testing Model -> Model
        assertEquals(indovinello.getTesto(), indovinello1.getTesto());
        assertEquals(indovinello.getRisposta(), indovinello1.getRisposta());
        assertEquals(indovinello.getIdScenario(), indovinello1.getIdScenario());
        assertEquals(indovinello.getIdScenarioRispostaCorretta(), indovinello1.getIdScenarioRispostaCorretta());
        assertEquals(indovinello.getIdScenarioRispostaSbagliata(), indovinello1.getIdScenarioRispostaSbagliata());
    }

    @Test
    void testMapperIndovinelloEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        IndovinelloEntity indovinello = creaIndovinelloEntity(1L, scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));

        IndovinelloDTO indovinelloDTO = indovinelloMapper.indovinelloEntityToIndovinelloDTO(indovinello);

        //Testing Entity -> DTO
        assertEquals(indovinello.getTesto(), indovinelloDTO.getTesto());
        assertEquals(indovinello.getRisposta(), indovinelloDTO.getRisposta());
        assertEquals(indovinello.getIdScenario().getId(), indovinelloDTO.getIdScenario().getId());
        assertEquals(indovinello.getIdScenarioRispostaCorretta().getId(), indovinelloDTO.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinello.getIdScenarioRispostaSbagliata().getId(), indovinelloDTO.getIdScenarioRispostaSbagliata().getId());

        IndovinelloEntity indovinello1 = indovinelloMapper.indovinelloDTOToIndovinelloEntity(indovinelloDTO);

        //Testing DTO -> Entity
        assertEquals(indovinelloDTO.getTesto(), indovinello1.getTesto());
        assertEquals(indovinelloDTO.getRisposta(), indovinello1.getRisposta());
        assertEquals(indovinelloDTO.getIdScenario().getId(), indovinello1.getIdScenario().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaCorretta().getId(), indovinello1.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinelloDTO.getIdScenarioRispostaSbagliata().getId(), indovinello1.getIdScenarioRispostaSbagliata().getId());

        //Testing Entity -> Entity
        assertEquals(indovinello.getTesto(), indovinello1.getTesto());
        assertEquals(indovinello.getRisposta(), indovinello1.getRisposta());
        assertEquals(indovinello.getIdScenario().getId(), indovinello1.getIdScenario().getId());
        assertEquals(indovinello.getIdScenarioRispostaCorretta().getId(), indovinello1.getIdScenarioRispostaCorretta().getId());
        assertEquals(indovinello.getIdScenarioRispostaSbagliata().getId(), indovinello1.getIdScenarioRispostaSbagliata().getId());
    }

    @Test
    void testMapperIndovinelloNull() {
        Indovinello indovinello = null;

        IndovinelloDTO indovinelloDTO = indovinelloMapper.indovinelloToIndovinelloDTO(indovinello);

        assertNull(indovinelloDTO);

        indovinello = indovinelloMapper.indovinelloDTOToIndovinello(indovinelloDTO);

        assertNull(indovinello);
    }

    @Test
    void testMapperIndovinelloEntityNull() {
        IndovinelloEntity indovinello = null;

        IndovinelloDTO indovinelloDTO = indovinelloMapper.indovinelloEntityToIndovinelloDTO(indovinello);

        assertNull(indovinelloDTO);

        indovinello = indovinelloMapper.indovinelloDTOToIndovinelloEntity(indovinelloDTO);

        assertNull(indovinello);
    }
}
