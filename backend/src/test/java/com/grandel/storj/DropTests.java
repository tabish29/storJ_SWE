package com.grandel.storj;

import com.grandel.storj.dto.*;
import com.grandel.storj.entity.DropEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Drop;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class DropTests extends AbstractTests {

    @Autowired
    private DropBL dropBL;
    @Autowired
    private OggettoBL oggettoBL;
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
    void testGetDropDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        DropDTO dropDTO = creaDropDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        DropDTO dropDTOcreated = dropBL.postDrop(dropDTO);

        DropDTO dropDTOreturned = dropBL.getDropDTOById(dropDTOcreated.getId());

        assertEquals(dropDTO.getIdOggetto().getId(), dropDTOreturned.getIdOggetto().getId());
        assertEquals(dropDTO.getIdScenario().getId(), dropDTOreturned.getIdScenario().getId());
    }

    @Test
    void testGetDropDTOByIdFail() {
        try {
            dropBL.getDropDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "DropNotFound - Drop non trovato!");
        }
    }

    @Test
    void testPostDrop() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        DropDTO dropDTO = creaDropDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        DropDTO dropDTOcreated = dropBL.postDrop(dropDTO);

        assertEquals(dropDTO.getIdOggetto().getId(), dropDTOcreated.getIdOggetto().getId());
        assertEquals(dropDTO.getIdScenario().getId(), dropDTOcreated.getIdScenario().getId());
    }

    @Test
    void testPutDrop() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        DropDTO dropDTO = creaDropDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        DropDTO dropDTOcreated = dropBL.postDrop(dropDTO);

        OggettoDTO oggettoDTO1 = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome cambiato", "descrizione");
        oggettoDTO1 = oggettoBL.postOggetto(oggettoDTO);

        dropDTO.setIdOggetto(oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO1));

        DropDTO dropDTOput = dropBL.putDrop(dropDTOcreated.getId(), dropDTO);

        assertEquals(dropDTO.getIdOggetto().getId(), dropDTOput.getIdOggetto().getId());
        assertEquals(dropDTO.getIdScenario().getId(), dropDTOput.getIdScenario().getId());
    }

    @Test
    void testPutDropFail() {
        try {
            dropBL.putDrop(1L, new DropDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "DropNotFound - Drop non trovato!");
        }
    }

    @Test
    void testDeleteDrop() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        DropDTO dropDTO = creaDropDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        DropDTO dropDTOcreated = dropBL.postDrop(dropDTO);

        dropBL.getDropDTOById(dropDTOcreated.getId());

        dropBL.deleteDrop(dropDTOcreated.getId());

        try {
            dropBL.getDropDTOById(dropDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "DropNotFound - Drop non trovato!");
        }
    }

    @Test
    void testGetDropByScenario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        DropDTO dropDTO = creaDropDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        DropDTO dropDTOcreated = dropBL.postDrop(dropDTO);

        assertEquals(dropDTOcreated.getIdOggetto().getId(), dropBL.getDropByScenario(scenarioDTO.getId()).getIdOggetto().getId());
        assertEquals(dropDTOcreated.getIdScenario().getId(), dropBL.getDropByScenario(scenarioDTO.getId()).getIdScenario().getId());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperDrop() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        Drop drop = creaDropModel(1L, scenarioDTO.getId(), oggettoDTO.getId());

        DropDTO dropDTO = dropMapper.dropToDropDTO(drop);

        //Testing Model -> DTO
        assertEquals(drop.getIdOggetto(), dropDTO.getIdOggetto().getId());
        assertEquals(drop.getIdScenario(), dropDTO.getIdScenario().getId());

        Drop drop1 = dropMapper.dropDTOToDrop(dropDTO);

        //Testing DTO -> Model
        assertEquals(dropDTO.getIdOggetto().getId(), drop1.getIdOggetto());
        assertEquals(dropDTO.getIdScenario().getId(), drop1.getIdScenario());

        //Testing Model -> Model
        assertEquals(drop.getIdOggetto(), drop1.getIdOggetto());
        assertEquals(drop.getIdScenario(), drop1.getIdScenario());
    }

    @Test
    void testMapperDropEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        DropEntity drop = creaDropEntity(1L, scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        DropDTO dropDTO = dropMapper.dropEntityToDropDTO(drop);

        //Testing Entity -> DTO
        assertEquals(drop.getIdOggetto().getId(), dropDTO.getIdOggetto().getId());
        assertEquals(drop.getIdScenario().getId(), dropDTO.getIdScenario().getId());

        DropEntity drop1 = dropMapper.dropDTOToDropEntity(dropDTO);

        //Testing DTO -> Entity
        assertEquals(dropDTO.getIdOggetto().getId(), drop1.getIdOggetto().getId());
        assertEquals(dropDTO.getIdScenario().getId(), drop1.getIdScenario().getId());

        //Testing Entity -> Entity
        assertEquals(drop.getIdOggetto().getId(), drop1.getIdOggetto().getId());
        assertEquals(drop.getIdScenario().getId(), drop1.getIdScenario().getId());
    }

    @Test
    void testMapperDropNull() {
        Drop drop = null;

        DropDTO dropDTO = dropMapper.dropToDropDTO(drop);

        assertNull(dropDTO);

        drop = dropMapper.dropDTOToDrop(dropDTO);

        assertNull(drop);
    }

    @Test
    void testMapperDropEntityNull() {
        DropEntity drop = null;

        DropDTO dropDTO = dropMapper.dropEntityToDropDTO(drop);

        assertNull(dropDTO);

        drop = dropMapper.dropDTOToDropEntity(dropDTO);

        assertNull(drop);
    }
}
