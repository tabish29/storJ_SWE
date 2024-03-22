package com.grandel.storj;

import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Storia;

import static org.junit.jupiter.api.Assertions.*;

public class StoriaTests extends AbstractTests {

    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private UtenteBL utenteBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetStoriaDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);

        StoriaDTO storiaDTOcreated = storiaBL.postStoria(storiaDTO);

        StoriaDTO storiaDTOreturned = storiaBL.getStoriaDTOById(storiaDTOcreated.getId());

        assertEquals(storiaDTO.getIdCreatore().getId(), storiaDTOreturned.getIdCreatore().getId());
        assertEquals(storiaDTO.getTitolo(), storiaDTOreturned.getTitolo());
        assertEquals(storiaDTO.getCategoria(), storiaDTOreturned.getCategoria());
        assertEquals(storiaDTO.getNumeroScenari(), storiaDTOreturned.getNumeroScenari());
        assertEquals(storiaDTO.getStatoCompletamento(), storiaDTOreturned.getStatoCompletamento());
    }

    @Test
    void testGetStoriaDTOByIdFail() {
        try {
            storiaBL.getStoriaDTOById(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "StoriaNotFound - Storia non trovata!");
        }
    }

    @Test
    void testPostStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);

        StoriaDTO storiaDTOcreated = storiaBL.postStoria(storiaDTO);

        assertEquals(storiaDTO.getIdCreatore().getId(), storiaDTOcreated.getIdCreatore().getId());
        assertEquals(storiaDTO.getTitolo(), storiaDTOcreated.getTitolo());
        assertEquals(storiaDTO.getCategoria(), storiaDTOcreated.getCategoria());
        assertEquals(storiaDTO.getNumeroScenari(), storiaDTOcreated.getNumeroScenari());
        assertEquals(storiaDTO.getStatoCompletamento(), storiaDTOcreated.getStatoCompletamento());
    }

    @Test
    void testPutStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);

        StoriaDTO storiaDTOcreated = storiaBL.postStoria(storiaDTO);

        storiaDTO.setNumeroScenari(2);

        StoriaDTO storiaDTOput = storiaBL.putStoria(storiaDTOcreated.getId(), storiaDTO);

        assertEquals(storiaDTO.getIdCreatore().getId(), storiaDTOput.getIdCreatore().getId());
        assertEquals(storiaDTO.getTitolo(), storiaDTOput.getTitolo());
        assertEquals(storiaDTO.getCategoria(), storiaDTOput.getCategoria());
        assertEquals(storiaDTO.getNumeroScenari(), storiaDTOput.getNumeroScenari());
        assertEquals(storiaDTO.getStatoCompletamento(), storiaDTOput.getStatoCompletamento());
    }

    @Test
    void testPutStoriaFail() {
        try {
            storiaBL.putStoria(1L, new StoriaDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "StoriaNotFound - Storia non trovata!");
        }
    }

    @Test
    void testDeleteStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);

        StoriaDTO storiaDTOcreated = storiaBL.postStoria(storiaDTO);

        storiaBL.getStoriaDTOById(storiaDTOcreated.getId());

        storiaBL.deleteStoria(storiaDTOcreated.getId());

        try {
            storiaBL.getStoriaDTOById(storiaDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "StoriaNotFound - Storia non trovata!");
        }
    }

    @Test
    void testGetStorieByUtente() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);

        storiaBL.postStoria(storiaDTO);
        storiaBL.postStoria(storiaDTO);

        assertEquals(2, storiaBL.getStorieByUtente(utenteDTO.getId()).size());
    }

    @Test
    void testGetStorieNoFilter() {
        loadStorie();

        assertEquals(3, storiaBL.getStorie(null, null, null).size());
    }

    @Test
    void testGetStorieFilterFail() {
        loadStorie();

        try {
            storiaBL.getStorie("prova", "prova", 1);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "FiltroError - Errore nei filtri!");
        }
    }

    @Test
    void testGetStorieAutore() {
        loadStorie();

        assertEquals(3, storiaBL.getStorie("Davide", null, null).size());

        try {
            assertEquals(0, storiaBL.getStorie("ossama", "ossama", null).size());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "UtenteNotFound - Utente non registrato!");
        }
    }

    @Test
    void testGetStorieCategoria() {
        loadStorie();

        assertEquals(2, storiaBL.getStorie(null, "categoria", null).size());
        assertEquals(1, storiaBL.getStorie(null, "ossama", null).size());
        assertEquals(0, storiaBL.getStorie(null, "matteo", null).size());
    }

    @Test
    void testGetStorieNumScenari() {
        loadStorie();

        assertEquals(1, storiaBL.getStorie(null, null, 2).size());
        assertEquals(1, storiaBL.getStorie(null, null, 1).size());
        assertEquals(0, storiaBL.getStorie(null, null, 10).size());
    }

    private void loadStorie() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 2, true);
        StoriaDTO storiaDTO1 = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 1, true);
        StoriaDTO storiaDTO2 = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "ossama", 0, true);

        storiaBL.postStoria(storiaDTO);
        storiaBL.postStoria(storiaDTO1);
        storiaBL.postStoria(storiaDTO2);
    }

    //SEZIONE MAPPER

    @Test
    void testMapperStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        Storia storia = creaStoriaModel(1L, utenteDTO.getId(), "titolo", "categoria", 2, true);

        StoriaDTO storiaDTO = storiaMapper.storiaToStoriaDTO(storia);

        //Testing Model -> DTO
        assertEquals(storia.getIdCreatore(), storiaDTO.getIdCreatore().getId());
        assertEquals(storia.getTitolo(), storiaDTO.getTitolo());
        assertEquals(storia.getCategoria(), storiaDTO.getCategoria());
        assertEquals(storia.getNumeroScenari(), storiaDTO.getNumeroScenari());
        assertEquals(storia.getStatoCompletamento(), storiaDTO.getStatoCompletamento());

        Storia storia1 = storiaMapper.storiaDTOToStoria(storiaDTO);

        //Testing DTO -> Model
        assertEquals(storiaDTO.getIdCreatore().getId(), storia1.getIdCreatore());
        assertEquals(storiaDTO.getTitolo(), storia1.getTitolo());
        assertEquals(storiaDTO.getCategoria(), storia1.getCategoria());
        assertEquals(storiaDTO.getNumeroScenari(), storia1.getNumeroScenari());
        assertEquals(storiaDTO.getStatoCompletamento(), storia1.getStatoCompletamento());

        //Testing Model -> Model
        assertEquals(storia.getIdCreatore(), storia1.getIdCreatore());
        assertEquals(storia.getTitolo(), storia1.getTitolo());
        assertEquals(storia.getCategoria(), storia1.getCategoria());
        assertEquals(storia.getNumeroScenari(), storia1.getNumeroScenari());
        assertEquals(storia.getStatoCompletamento(), storia1.getStatoCompletamento());
    }

    @Test
    void testMapperStoriaEntity() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaEntity storia = creaStoriaEntity(1L, utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 2, true);

        StoriaDTO storiaDTO = storiaMapper.storiaEntityToStoriaDTO(storia);

        //Testing Entity -> DTO
        assertEquals(storia.getIdCreatore().getId(), storiaDTO.getIdCreatore().getId());
        assertEquals(storia.getTitolo(), storiaDTO.getTitolo());
        assertEquals(storia.getCategoria(), storiaDTO.getCategoria());
        assertEquals(storia.getNumeroScenari(), storiaDTO.getNumeroScenari());
        assertEquals(storia.getStatoCompletamento(), storiaDTO.getStatoCompletamento());

        StoriaEntity storia1 = storiaMapper.storiaDTOToStoriaEntity(storiaDTO);

        //Testing DTO -> Entity
        assertEquals(storiaDTO.getIdCreatore().getId(), storia1.getIdCreatore().getId());
        assertEquals(storiaDTO.getTitolo(), storia1.getTitolo());
        assertEquals(storiaDTO.getCategoria(), storia1.getCategoria());
        assertEquals(storiaDTO.getNumeroScenari(), storia1.getNumeroScenari());
        assertEquals(storiaDTO.getStatoCompletamento(), storia1.getStatoCompletamento());

        //Testing Entity -> Entity
        assertEquals(storia.getIdCreatore().getId(), storia1.getIdCreatore().getId());
        assertEquals(storia.getTitolo(), storia1.getTitolo());
        assertEquals(storia.getCategoria(), storia1.getCategoria());
        assertEquals(storia.getNumeroScenari(), storia1.getNumeroScenari());
        assertEquals(storia.getStatoCompletamento(), storia1.getStatoCompletamento());
    }

    @Test
    void testMapperStoriaNull() {
        Storia storia = null;

        StoriaDTO storiaDTO = storiaMapper.storiaToStoriaDTO(storia);

        assertNull(storiaDTO);

        storia = storiaMapper.storiaDTOToStoria(storiaDTO);

        assertNull(storia);
    }

    @Test
    void testMapperStoriaEntityNull() {
        StoriaEntity storia = null;

        StoriaDTO storiaDTO = storiaMapper.storiaEntityToStoriaDTO(storia);

        assertNull(storiaDTO);

        storia = storiaMapper.storiaDTOToStoriaEntity(storiaDTO);

        assertNull(storia);
    }
}
