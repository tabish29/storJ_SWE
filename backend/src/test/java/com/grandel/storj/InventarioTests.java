package com.grandel.storj;

import com.grandel.storj.dto.*;
import com.grandel.storj.entity.InventarioEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Inventario;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.*;

public class InventarioTests extends AbstractTests {

    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private UtenteBL utenteBL;
    @Autowired
    private PartitaBL partitaBL;
    @Autowired
    private OggettoBL oggettoBL;
    @Autowired
    private InventarioBL inventarioBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetInventarioDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        InventarioDTO inventarioDTO = creaInventarioDTO(partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        InventarioDTO inventarioDTOcreated = inventarioBL.postInventario(inventarioDTO);

        InventarioDTO inventarioDTOreturned = inventarioBL.postInventario(inventarioDTO);

        assertEquals(inventarioDTO.getIdOggetto().getId(), inventarioDTOreturned.getIdOggetto().getId());
        assertEquals(inventarioDTO.getIdPartita().getId(), inventarioDTOreturned.getIdPartita().getId());
    }

    @Test
    void testGetInventarioDTOByIdFail() {
        try {
            inventarioBL.getInventarioDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "InventarioNotFound - Inventario non trovato!");
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
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        InventarioDTO inventarioDTO = creaInventarioDTO(partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        InventarioDTO inventarioDTOcreated = inventarioBL.postInventario(inventarioDTO);

        assertEquals(inventarioDTO.getIdOggetto().getId(), inventarioDTOcreated.getIdOggetto().getId());
        assertEquals(inventarioDTO.getIdPartita().getId(), inventarioDTOcreated.getIdPartita().getId());
    }

    @Test
    void testPutPartita() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        OggettoDTO oggettoDTO1 = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome cambiato", "descrizione");
        oggettoDTO1 = oggettoBL.postOggetto(oggettoDTO1);

        InventarioDTO inventarioDTO = creaInventarioDTO(partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        InventarioDTO inventarioDTOcreated = inventarioBL.postInventario(inventarioDTO);

        inventarioDTO.setIdOggetto(oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO1));

        InventarioDTO inventarioDTOput = inventarioBL.putInventario(inventarioDTOcreated.getId(), inventarioDTO);

        assertEquals(inventarioDTO.getIdOggetto().getId(), inventarioDTOput.getIdOggetto().getId());
        assertEquals(inventarioDTO.getIdPartita().getId(), inventarioDTOput.getIdPartita().getId());
    }

    @Test
    void testPutPartitaFail() {
        try {
            inventarioBL.putInventario(1L, new InventarioDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "InventarioNotFound - Inventario non trovato!");
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

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        InventarioDTO inventarioDTO = creaInventarioDTO(partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        InventarioDTO inventarioDTOcreated = inventarioBL.postInventario(inventarioDTO);

        inventarioBL.getInventarioDTOById(inventarioDTOcreated.getId());

        inventarioBL.deleteInventario(inventarioDTOcreated.getId());

        try {
            inventarioBL.getInventarioDTOById(inventarioDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "InventarioNotFound - Inventario non trovato!");
        }
    }

    @Test
    void testGetOggettiByPartita() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        InventarioDTO inventarioDTO = creaInventarioDTO(partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        inventarioBL.postInventario(inventarioDTO);

        assertEquals(1, inventarioBL.getOggettiByPartita(partitaDTO.getId()).size());

        OggettoDTO oggettoDTO1 = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome cambiato", "descrizione");
        oggettoDTO1 = oggettoBL.postOggetto(oggettoDTO1);

        InventarioDTO inventarioDTO1 = creaInventarioDTO(partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO1));

        inventarioBL.postInventario(inventarioDTO1);

        assertEquals(2, inventarioBL.getOggettiByPartita(partitaDTO.getId()).size());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperInventario() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        Inventario inventario = creaInventarioModel(1L, partitaDTO.getId(), oggettoDTO.getId());

        InventarioDTO inventarioDTO = inventarioMapper.inventarioToInventarioDTO(inventario);

        //Testing Model -> DTO
        assertEquals(inventario.getIdOggetto(), inventarioDTO.getIdOggetto().getId());
        assertEquals(inventario.getIdPartita(), inventarioDTO.getIdPartita().getId());

        Inventario inventario1 = inventarioMapper.inventarioDTOToInventario(inventarioDTO);

        //Testing DTO -> Model
        assertEquals(inventarioDTO.getIdOggetto().getId(), inventario1.getIdOggetto());
        assertEquals(inventarioDTO.getIdPartita().getId(), inventario1.getIdPartita());

        //Testing Model -> Model
        assertEquals(inventario.getIdOggetto(), inventario1.getIdOggetto());
        assertEquals(inventario.getIdPartita(), inventario1.getIdPartita());
    }

    @Test
    void testMapperInventarioEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        PartitaDTO partitaDTO = creaPartitaDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), utenteMapper.utenteDTOToUtenteEntity(utenteDTO), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO));
        partitaDTO = partitaBL.postPartita(partitaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");
        oggettoDTO = oggettoBL.postOggetto(oggettoDTO);

        InventarioEntity inventario = creaInventarioEntity(1L, partitaMapper.partitaDTOToPartitaEntity(partitaDTO), oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO));

        InventarioDTO inventarioDTO = inventarioMapper.inventarioEntityToInventarioDTO(inventario);

        //Testing Entity -> DTO
        assertEquals(inventario.getIdOggetto().getId(), inventarioDTO.getIdOggetto().getId());
        assertEquals(inventario.getIdPartita().getId(), inventarioDTO.getIdPartita().getId());

        InventarioEntity inventario1 = inventarioMapper.inventarioDTOTOInventarioEntity(inventarioDTO);

        //Testing DTO -> Entity
        assertEquals(inventarioDTO.getIdOggetto().getId(), inventario1.getIdOggetto().getId());
        assertEquals(inventarioDTO.getIdPartita().getId(), inventario1.getIdPartita().getId());

        //Testing Entity -> Entity
        assertEquals(inventario.getIdOggetto().getId(), inventario1.getIdOggetto().getId());
        assertEquals(inventario.getIdPartita().getId(), inventario1.getIdPartita().getId());
    }

    @Test
    void testMapperInventarioNull() {
        Inventario inventario = null;

        InventarioDTO inventarioDTO = inventarioMapper.inventarioToInventarioDTO(inventario);

        assertNull(inventarioDTO);

        inventario = inventarioMapper.inventarioDTOToInventario(inventarioDTO);

        assertNull(inventario);
    }

    @Test
    void testMapperInventarioEntityNull() {
        InventarioEntity inventario = null;

        InventarioDTO inventarioDTO = inventarioMapper.inventarioEntityToInventarioDTO(inventario);

        assertNull(inventarioDTO);

        inventario = inventarioMapper.inventarioDTOTOInventarioEntity(inventarioDTO);

        assertNull(inventario);
    }
}
