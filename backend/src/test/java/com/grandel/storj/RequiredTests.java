package com.grandel.storj;

import com.grandel.storj.dto.*;
import com.grandel.storj.entity.RequiredEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Required;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class RequiredTests extends AbstractTests {

    @Autowired
    private OggettoBL oggettoBL;
    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private UtenteBL utenteBL;
    @Autowired
    private RequiredBL requiredBL;
    @Autowired
    private MultiplaBL multiplaBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetRequiredDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        RequiredDTO requiredDTO = creaRequiredDTO(multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        RequiredDTO requiredDTOcreated = requiredBL.postRequired(requiredDTO);

        RequiredDTO requiredDTOreturned = requiredBL.getRequiredDTOById(requiredDTOcreated.getId());

        assertEquals(requiredDTO.getIdOggetto().getId(), requiredDTOreturned.getIdOggetto().getId());
        assertEquals(requiredDTO.getIdScelta().getId(), requiredDTOreturned.getIdScelta().getId());
    }

    @Test
    void testGetRequiredDTOByIdFail() {
        try {
            requiredBL.getRequiredDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "RequiredNotFound - Required non trovato!");
        }
    }

    @Test
    void testPostRequired() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        RequiredDTO requiredDTO = creaRequiredDTO(multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        RequiredDTO requiredDTOcreated = requiredBL.postRequired(requiredDTO);

        assertEquals(requiredDTO.getIdOggetto().getId(), requiredDTOcreated.getIdOggetto().getId());
        assertEquals(requiredDTO.getIdScelta().getId(), requiredDTOcreated.getIdScelta().getId());
    }

    @Test
    void testPutRequired() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        RequiredDTO requiredDTO = creaRequiredDTO(multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        RequiredDTO requiredDTOcreated = requiredBL.postRequired(requiredDTO);

        OggettoDTO oggettoDTO1 = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome cambiato", "descrizione");
        oggettoDTO1 = oggettoBL.postOggetto(oggettoDTO1);

        requiredDTO.setIdOggetto(oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO1));

        RequiredDTO requiredDTOput = requiredBL.putRequired(requiredDTOcreated.getId(), requiredDTO);

        assertEquals(requiredDTO.getIdOggetto().getId(), requiredDTOput.getIdOggetto().getId());
        assertEquals(requiredDTO.getIdScelta().getId(), requiredDTOput.getIdScelta().getId());
    }

    @Test
    void testPutRequiredFail() {
        try {
            requiredBL.putRequired(1L, new RequiredDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "RequiredNotFound - Required non trovato!");
        }
    }

    @Test
    void testDeleteRequired() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        RequiredDTO requiredDTO = creaRequiredDTO(multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        RequiredDTO requiredDTOcreated = requiredBL.postRequired(requiredDTO);

        requiredBL.getRequiredDTOById(requiredDTOcreated.getId());

        requiredBL.deleteRequired(requiredDTOcreated.getId());

        try {
            requiredBL.getRequiredDTOById(requiredDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "RequiredNotFound - Required non trovato!");
        }
    }

    @Test
    void testGetRequiredByScelta() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        RequiredDTO requiredDTO = creaRequiredDTO(multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        RequiredDTO requiredDTOcreated = requiredBL.postRequired(requiredDTO);

        assertEquals(requiredDTOcreated.getIdScelta().getId(), requiredBL.getRequiredByScelta(multiplaDTO.getId()).getIdScelta().getId());
        assertEquals(requiredDTOcreated.getIdOggetto().getId(), requiredBL.getRequiredByScelta(multiplaDTO.getId()).getIdOggetto().getId());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperRequired() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        Required required = creaRequiredModel(1L, multiplaDTO.getId(), oggettoDTO.getId());

        RequiredDTO requiredDTO = requiredMapper.requiredToRequiredDTO(required);

        //Testing Model -> DTO
        assertEquals(required.getIdOggetto(), requiredDTO.getIdOggetto().getId());
        assertEquals(required.getIdScelta(), requiredDTO.getIdScelta().getId());

        Required required1 = requiredMapper.requiredDTOToRequired(requiredDTO);

        //Testing DTO -> Model
        assertEquals(requiredDTO.getIdOggetto().getId(), required1.getIdOggetto());
        assertEquals(requiredDTO.getIdScelta().getId(), required1.getIdScelta());

        //Testing Model -> Model
        assertEquals(required.getIdOggetto(), required1.getIdOggetto());
        assertEquals(required.getIdScelta(), required1.getIdScelta());
    }

    @Test
    void testMapperRequiredEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        RequiredEntity required = creaRequiredEntity(1L, multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        RequiredDTO requiredDTO = requiredMapper.requiredEntityToRequiredDTO(required);

        //Testing Entity -> DTO
        assertEquals(required.getIdOggetto().getId(), requiredDTO.getIdOggetto().getId());
        assertEquals(required.getIdScelta().getId(), requiredDTO.getIdScelta().getId());

        RequiredEntity required1 = requiredMapper.requiredDTOToRequiredEntity(requiredDTO);

        //Testing DTO -> Entity
        assertEquals(requiredDTO.getIdOggetto().getId(), required1.getIdOggetto().getId());
        assertEquals(requiredDTO.getIdScelta().getId(), required1.getIdScelta().getId());

        //Testing Entity -> Entity
        assertEquals(required.getIdOggetto().getId(), required1.getIdOggetto().getId());
        assertEquals(required.getIdScelta().getId(), required1.getIdScelta().getId());
    }

    @Test
    void testMapperRequiredNull() {
        Required required = null;

        RequiredDTO requiredDTO = requiredMapper.requiredToRequiredDTO(required);

        assertNull(requiredDTO);

        required = requiredMapper.requiredDTOToRequired(requiredDTO);

        assertNull(required);
    }

    @Test
    void testMapperRequiredEntityNull() {
        RequiredEntity required = null;

        RequiredDTO requiredDTO = requiredMapper.requiredEntityToRequiredDTO(required);

        assertNull(requiredDTO);

        required = requiredMapper.requiredDTOToRequiredEntity(requiredDTO);

        assertNull(required);
    }
}
