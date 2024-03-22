package com.grandel.storj;

import com.grandel.storj.dto.MultiplaDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.MultiplaEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.MultiplaBL;
import com.grandel.storj.service.ScenarioBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Multipla;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class MultiplaTests extends AbstractTests {

    @Autowired
    private MultiplaBL multiplaBL;
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
    void testGetMultiplaDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        MultiplaDTO multiplaDTOcreated = multiplaBL.postMultipla(multiplaDTO);

        MultiplaDTO multiplaDTOreturned = multiplaBL.getMultiplaDTOById(multiplaDTOcreated.getId());

        assertEquals(multiplaDTO.getIdScenario().getId(), multiplaDTOreturned.getIdScenario().getId());
        assertEquals(multiplaDTO.getIdScenarioSuccessivo().getId(), multiplaDTOreturned.getIdScenarioSuccessivo().getId());
        assertEquals(multiplaDTO.getTesto(), multiplaDTOreturned.getTesto());
    }

    @Test
    void testGetMultiplaDTOByIdFail() {
        try {
            multiplaBL.getMultiplaDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "MultiplaNotFound - Multipla non trovata!");
        }
    }

    @Test
    void testPostMultipla() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        MultiplaDTO multiplaDTOcreated = multiplaBL.postMultipla(multiplaDTO);

        assertEquals(multiplaDTO.getIdScenario().getId(), multiplaDTOcreated.getIdScenario().getId());
        assertEquals(multiplaDTO.getIdScenarioSuccessivo().getId(), multiplaDTOcreated.getIdScenarioSuccessivo().getId());
        assertEquals(multiplaDTO.getTesto(), multiplaDTOcreated.getTesto());
    }

    @Test
    void testPutMultipla() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        MultiplaDTO multiplaDTOcreated = multiplaBL.postMultipla(multiplaDTO);

        multiplaDTO.setTesto("testo2");

        MultiplaDTO multiplaDTOput = multiplaBL.putMultipla(multiplaDTOcreated.getId(), multiplaDTO);

        assertEquals(multiplaDTO.getIdScenario().getId(), multiplaDTOput.getIdScenario().getId());
        assertEquals(multiplaDTO.getIdScenarioSuccessivo().getId(), multiplaDTOput.getIdScenarioSuccessivo().getId());
        assertEquals(multiplaDTO.getTesto(), multiplaDTOput.getTesto());
    }

    @Test
    void testPutMultiplaFail() {
        try {
            multiplaBL.putMultipla(1L, new MultiplaDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "MultiplaNotFound - Multipla non trovata!");
        }
    }

    @Test
    void testDeleteMultipla() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        MultiplaDTO multiplaDTOcreated = multiplaBL.postMultipla(multiplaDTO);

        multiplaBL.getMultiplaDTOById(multiplaDTOcreated.getId());

        multiplaBL.deleteMultipla(multiplaDTOcreated.getId());

        try {
            multiplaBL.getMultiplaDTOById(multiplaDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "MultiplaNotFound - Multipla non trovata!");
        }
    }

    @Test
    void testGetMultipleByScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        multiplaBL.postMultipla(multiplaDTO);
        multiplaBL.postMultipla(multiplaDTO);

        assertEquals(2, multiplaBL.getMultipleByScenario(scenarioDTO.getId()).size());
        assertEquals(0, multiplaBL.getMultipleByScenario(scenarioDTO1.getId()).size());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperMultipla() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        Multipla multipla = creaMultiplaModel(1L, scenarioDTO.getId(), "testo", scenarioDTO1.getId());

        MultiplaDTO multiplaDTO = multiplaMapper.multiplaToMultiplaDTO(multipla);

        //Testing Model -> DTO
        assertEquals(multipla.getIdScenario(), multiplaDTO.getIdScenario().getId());
        assertEquals(multipla.getIdScenarioSuccessivo(), multiplaDTO.getIdScenarioSuccessivo().getId());
        assertEquals(multipla.getTesto(), multiplaDTO.getTesto());

        Multipla multipla1 = multiplaMapper.multiplaDTOToMultipla(multiplaDTO);

        //Testing DTO -> Model
        assertEquals(multiplaDTO.getIdScenario().getId(), multipla1.getIdScenario());
        assertEquals(multiplaDTO.getIdScenarioSuccessivo().getId(), multipla1.getIdScenarioSuccessivo());
        assertEquals(multiplaDTO.getTesto(), multipla1.getTesto());

        //Testing Model -> Model
        assertEquals(multipla.getIdScenario(), multipla1.getIdScenario());
        assertEquals(multipla.getIdScenarioSuccessivo(), multipla1.getIdScenarioSuccessivo());
        assertEquals(multipla.getTesto(), multipla1.getTesto());
    }

    @Test
    void testMapperMultiplaEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaEntity multipla = creaMultiplaEntity(1L, scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        MultiplaDTO multiplaDTO = multiplaMapper.multiplaEntityToMultiplaDTO(multipla);

        //Testing Entity -> DTO
        assertEquals(multipla.getIdScenario().getId(), multiplaDTO.getIdScenario().getId());
        assertEquals(multipla.getIdScenarioSuccessivo().getId(), multiplaDTO.getIdScenarioSuccessivo().getId());
        assertEquals(multipla.getTesto(), multiplaDTO.getTesto());

        MultiplaEntity multipla1 = multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO);

        //Testing DTO -> Entity
        assertEquals(multiplaDTO.getIdScenario().getId(), multipla1.getIdScenario().getId());
        assertEquals(multiplaDTO.getIdScenarioSuccessivo().getId(), multipla1.getIdScenarioSuccessivo().getId());
        assertEquals(multiplaDTO.getTesto(), multipla1.getTesto());

        //Testing Entity -> Entity
        assertEquals(multipla.getIdScenario().getId(), multipla1.getIdScenario().getId());
        assertEquals(multipla.getIdScenarioSuccessivo().getId(), multipla1.getIdScenarioSuccessivo().getId());
        assertEquals(multipla.getTesto(), multipla1.getTesto());
    }

    @Test
    void testMapperMultiplaNull() {
        Multipla multipla = null;

        MultiplaDTO multiplaDTO = multiplaMapper.multiplaToMultiplaDTO(multipla);

        assertNull(multiplaDTO);

        multipla = multiplaMapper.multiplaDTOToMultipla(multiplaDTO);

        assertNull(multipla);
    }

    @Test
    void testMapperMultiplaEntityNull() {
        MultiplaEntity multipla = null;

        MultiplaDTO multiplaDTO = multiplaMapper.multiplaEntityToMultiplaDTO(multipla);

        assertNull(multiplaDTO);

        multipla = multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO);

        assertNull(multipla);
    }
}
