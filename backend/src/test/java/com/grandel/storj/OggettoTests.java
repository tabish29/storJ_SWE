package com.grandel.storj;

import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.OggettoBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Oggetto;

import static org.junit.jupiter.api.Assertions.*;

public class OggettoTests extends AbstractTests {

    @Autowired
    private UtenteBL utenteBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private OggettoBL oggettoBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetOggettoDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");

        OggettoDTO oggettoDTOcreated = oggettoBL.postOggetto(oggettoDTO);

        OggettoDTO oggettoDTOreturned = oggettoBL.getOggettoDTOById(oggettoDTOcreated.getId());

        assertEquals(oggettoDTO.getIdStoria().getId(), oggettoDTOreturned.getIdStoria().getId());
        assertEquals(oggettoDTO.getNome(), oggettoDTOreturned.getNome());
        assertEquals(oggettoDTO.getDescrizione(), oggettoDTOreturned.getDescrizione());
    }

    @Test
    void testGetOggettoDTOByIdFail() {
        try {
            oggettoBL.getOggettoDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "OggettoNotFound - Oggetto non trovato!");
        }
    }

    @Test
    void testPostOggetto() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");

        OggettoDTO oggettoDTOcreated = oggettoBL.postOggetto(oggettoDTO);

        assertEquals(oggettoDTO.getIdStoria().getId(), oggettoDTOcreated.getIdStoria().getId());
        assertEquals(oggettoDTO.getNome(), oggettoDTOcreated.getNome());
        assertEquals(oggettoDTO.getDescrizione(), oggettoDTOcreated.getDescrizione());
    }

    @Test
    void testPutOggetto() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");

        OggettoDTO oggettoDTOcreated = oggettoBL.postOggetto(oggettoDTO);

        oggettoDTO.setNome("nome cambiato");

        OggettoDTO oggettoDTOput = oggettoBL.putOggetto(oggettoDTOcreated.getId(), oggettoDTO);

        assertEquals(oggettoDTO.getIdStoria().getId(), oggettoDTOput.getIdStoria().getId());
        assertEquals(oggettoDTO.getNome(), oggettoDTOput.getNome());
        assertEquals(oggettoDTO.getDescrizione(), oggettoDTOput.getDescrizione());
    }

    @Test
    void testPutOggettoFail() {
        try {
            oggettoBL.putOggetto(1L, new OggettoDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "OggettoNotFound - Oggetto non trovato!");
        }
    }

    @Test
    void testDeleteOggetto() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");

        OggettoDTO oggettoDTOcreated = oggettoBL.postOggetto(oggettoDTO);

        oggettoBL.getOggettoDTOById(oggettoDTOcreated.getId());

        oggettoBL.deleteOggetto(oggettoDTOcreated.getId());

        try {
            oggettoBL.getOggettoDTOById(oggettoDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "OggettoNotFound - Oggetto non trovato!");
        }
    }

    @Test
    void testGetOggettiByStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        OggettoDTO oggettoDTO = creaOggettoDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");

        oggettoBL.postOggetto(oggettoDTO);
        oggettoBL.postOggetto(oggettoDTO);

        assertEquals(2, oggettoBL.getOggettiByStoria(storiaDTO.getId()).size());
    }

    //SEZIONE MAPPER

    @Test
    void testMapperOggetto() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        Oggetto oggetto = creaOggettoModel(1L, storiaDTO.getId(), "nome", "descrizione");

        OggettoDTO oggettoDTO = oggettoMapper.oggettoToOggettoDTO(oggetto);

        //Testing Model -> DTO
        assertEquals(oggetto.getIdStoria(), oggettoDTO.getIdStoria().getId());
        assertEquals(oggetto.getNome(), oggettoDTO.getNome());
        assertEquals(oggetto.getDescrizione(), oggettoDTO.getDescrizione());

        Oggetto oggetto1 = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);

        //Testing DTO -> Model
        assertEquals(oggettoDTO.getIdStoria().getId(), oggetto1.getIdStoria());
        assertEquals(oggettoDTO.getNome(), oggetto1.getNome());
        assertEquals(oggettoDTO.getDescrizione(), oggetto1.getDescrizione());

        //Testing Model -> Model
        assertEquals(oggetto.getIdStoria(), oggetto1.getIdStoria());
        assertEquals(oggetto.getNome(), oggetto1.getNome());
        assertEquals(oggetto.getDescrizione(), oggetto1.getDescrizione());
    }

    @Test
    void testMapperOggettoEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        OggettoEntity oggetto = creaOggettoEntity(1L, storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "nome", "descrizione");

        OggettoDTO oggettoDTO = oggettoMapper.oggettoEntityToOggettoDTO(oggetto);

        //Testing Entity -> DTO
        assertEquals(oggetto.getIdStoria().getId(), oggettoDTO.getIdStoria().getId());
        assertEquals(oggetto.getNome(), oggettoDTO.getNome());
        assertEquals(oggetto.getDescrizione(), oggettoDTO.getDescrizione());

        OggettoEntity oggetto1 = oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);

        //Testing DTO -> Entity
        assertEquals(oggettoDTO.getIdStoria().getId(), oggetto1.getIdStoria().getId());
        assertEquals(oggettoDTO.getNome(), oggetto1.getNome());
        assertEquals(oggettoDTO.getDescrizione(), oggetto1.getDescrizione());

        //Testing Entity -> Entity
        assertEquals(oggetto.getIdStoria().getId(), oggetto1.getIdStoria().getId());
        assertEquals(oggetto.getNome(), oggetto1.getNome());
        assertEquals(oggetto.getDescrizione(), oggetto1.getDescrizione());
    }

    @Test
    void testMapperOggettoNull() {
        Oggetto oggetto = null;

        OggettoDTO oggettoDTO = oggettoMapper.oggettoToOggettoDTO(oggetto);

        assertNull(oggettoDTO);

        oggetto = oggettoMapper.oggettoDTOToOggetto(oggettoDTO);

        assertNull(oggetto);
    }

    @Test
    void testMapperOggettoEntityNull() {
        OggettoEntity oggetto = null;

        OggettoDTO oggettoDTO = oggettoMapper.oggettoEntityToOggettoDTO(oggetto);

        assertNull(oggettoDTO);

        oggetto = oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);

        assertNull(oggetto);
    }
}
