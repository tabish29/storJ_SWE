package com.grandel.storj;

import com.grandel.storj.dto.PartitaDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.PartitaEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.PartitaBL;
import com.grandel.storj.service.ScenarioBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Partita;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class PartitaTests extends AbstractTests {

    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private UtenteBL utenteBL;
    @Autowired
    private PartitaBL partitaBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetPartitaDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));

        PartitaDTO partitaDTOcreated = partitaBL.postPartita(partitaDTO);

        PartitaDTO partitaDTOreturned = partitaBL.getPartitaDTOById(partitaDTOcreated.getId());

        assertEquals(partitaDTO.getIdStoria().getId(), partitaDTOreturned.getIdStoria().getId());
        assertEquals(partitaDTO.getIdUtente().getId(), partitaDTOreturned.getIdUtente().getId());
        assertEquals(partitaDTO.getIdScenarioCorrente().getId(), partitaDTOreturned.getIdScenarioCorrente().getId());
    }

    @Test
    void testGetPartitaDTOByIdFail() {
        try {
            partitaBL.getPartitaDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "PartitaNotFound - Partita non trovata!");
        }
    }

    @Test
    void testPostPartita() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));

        PartitaDTO partitaDTOcreated = partitaBL.postPartita(partitaDTO);

        assertEquals(partitaDTO.getIdStoria().getId(), partitaDTOcreated.getIdStoria().getId());
        assertEquals(partitaDTO.getIdUtente().getId(), partitaDTOcreated.getIdUtente().getId());
        assertEquals(partitaDTO.getIdScenarioCorrente().getId(), partitaDTOcreated.getIdScenarioCorrente().getId());
    }

    @Test
    void testPutPartita() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));

        PartitaDTO partitaDTOcreated = partitaBL.postPartita(partitaDTO);

        partitaDTO.setIdScenarioCorrente(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));

        PartitaDTO partitaDTOput = partitaBL.putPartita(partitaDTOcreated.getId(), partitaDTO);

        assertEquals(partitaDTO.getIdStoria().getId(), partitaDTOput.getIdStoria().getId());
        assertEquals(partitaDTO.getIdUtente().getId(), partitaDTOput.getIdUtente().getId());
        assertEquals(partitaDTO.getIdScenarioCorrente().getId(), partitaDTOput.getIdScenarioCorrente().getId());
    }

    @Test
    void testPutPartitaFail() {
        try {
            partitaBL.putPartita(1L, new PartitaDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "PartitaNotFound - Partita non trovata!");
        }
    }

    @Test
    void testDeletePartita() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));

        PartitaDTO partitaDTOcreated = partitaBL.postPartita(partitaDTO);

        partitaBL.getPartitaDTOById(partitaDTOcreated.getId());

        partitaBL.deletePartita(partitaDTOcreated.getId());

        try {
            partitaBL.getPartitaDTOById(partitaDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "PartitaNotFound - Partita non trovata!");
        }
    }

    @Test
    void testGetPartiteByUtente() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        UtenteDTO utenteDTO1 = creaUtenteDTO("Ossama", "1234", false);
        utenteDTO1 = utenteBL.postUtente(utenteDTO1);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, true);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));

        partitaBL.postPartita(partitaDTO);

        assertEquals(1, partitaBL.getPartiteByUtente(utenteDTO.getId()).size());
        assertEquals(0, partitaBL.getPartiteByUtente(utenteDTO1.getId()).size());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperPartita() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        Partita partita = creaPartitaModel(1L, storiaDTO.getId(), utenteDTO.getId(), scenarioDTO.getId());

        PartitaDTO partitaDTO = partitaMapper.partitaToPartitaDTO(partita);

        //Testing Model -> DTO
        assertEquals(partita.getIdStoria(), partitaDTO.getIdStoria().getId());
        assertEquals(partita.getIdUtente(), partitaDTO.getIdUtente().getId());
        assertEquals(partita.getIdScenarioCorrente(), partitaDTO.getIdScenarioCorrente().getId());

        Partita partita1 = partitaMapper.partitaDTOToPartita(partitaDTO);

        //Testing DTO -> Model
        assertEquals(partitaDTO.getIdStoria().getId(), partita1.getIdStoria());
        assertEquals(partitaDTO.getIdUtente().getId(), partita1.getIdUtente());
        assertEquals(partitaDTO.getIdScenarioCorrente().getId(), partita1.getIdScenarioCorrente());

        //Testing Model -> Model
        assertEquals(partita.getIdStoria(), partita1.getIdStoria());
        assertEquals(partita.getIdUtente(), partita1.getIdUtente());
        assertEquals(partita.getIdScenarioCorrente(), partita1.getIdScenarioCorrente());
    }

    @Test
    void testMapperPartitaEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaEntity partita = creaPartitaEntity(1L, storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));

        PartitaDTO partitaDTO = partitaMapper.partitaEntityToPartitaDTO(partita);

        //Testing Entity -> DTO
        assertEquals(partita.getIdStoria().getId(), partitaDTO.getIdStoria().getId());
        assertEquals(partita.getIdUtente().getId(), partitaDTO.getIdUtente().getId());
        assertEquals(partita.getIdScenarioCorrente().getId(), partitaDTO.getIdScenarioCorrente().getId());

        PartitaEntity partita1 = partitaMapper.partitaDTOToPartitaEntity(partitaDTO);

        //Testing DTO -> Entity
        assertEquals(partitaDTO.getIdStoria().getId(), partita1.getIdStoria().getId());
        assertEquals(partitaDTO.getIdUtente().getId(), partita1.getIdUtente().getId());
        assertEquals(partitaDTO.getIdScenarioCorrente().getId(), partita1.getIdScenarioCorrente().getId());

        //Testing Entity -> Entity
        assertEquals(partita.getIdStoria().getId(), partita1.getIdStoria().getId());
        assertEquals(partita.getIdUtente().getId(), partita1.getIdUtente().getId());
        assertEquals(partita.getIdScenarioCorrente().getId(), partita1.getIdScenarioCorrente().getId());
    }

    @Test
    void testMapperPartitaNull() {
        Partita partita = null;

        PartitaDTO partitaDTO = partitaMapper.partitaToPartitaDTO(partita);

        assertNull(partitaDTO);

        partita = partitaMapper.partitaDTOToPartita(partitaDTO);

        assertNull(partita);
    }

    @Test
    void testMapperPartitaEntityNull() {
        PartitaEntity partita = null;

        PartitaDTO partitaDTO = partitaMapper.partitaEntityToPartitaDTO(partita);

        assertNull(partitaDTO);

        partita = partitaMapper.partitaDTOToPartitaEntity(partitaDTO);

        assertNull(partita);
    }
}
