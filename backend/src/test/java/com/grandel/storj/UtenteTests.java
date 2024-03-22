package com.grandel.storj;

import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.UtenteBL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Utente;

import static org.junit.jupiter.api.Assertions.*;

public class UtenteTests extends AbstractTests {

    @Autowired
    private UtenteBL utenteBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testGetUtenteDTOById() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);

        UtenteDTO utenteDTOcreated = utenteBL.postUtente(utenteDTO);

        UtenteDTO utenteDTOreturned = utenteBL.getUtenteDTObyId(utenteDTOcreated.getId());

        assertEquals(utenteDTO.getUsername(), utenteDTOreturned.getUsername());
        assertEquals(utenteDTO.getPassword(), utenteDTOreturned.getPassword());
        assertEquals(utenteDTO.isStatoPagamento(), utenteDTOreturned.isStatoPagamento());
    }

    @Test
    void testGetUtenteDTOByUsername() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);

        UtenteDTO utenteDTOcreated = utenteBL.postUtente(utenteDTO);

        UtenteDTO utenteDTOreturned = utenteBL.getUtenteDTOByUsername(utenteDTOcreated.getUsername());

        assertEquals(utenteDTO.getUsername(), utenteDTOreturned.getUsername());
        assertEquals(utenteDTO.getPassword(), utenteDTOreturned.getPassword());
        assertEquals(utenteDTO.isStatoPagamento(), utenteDTOreturned.isStatoPagamento());
    }

    @Test
    void testGetUtenteDTOByIdFail() {
        try {
            utenteBL.getUtenteDTObyId(1L);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "UtenteNotFound - Utente non registrato!");
        }
    }

    @Test
    void testGetUtenteDTOByUsernameFail() {
        try {
            utenteBL.getUtenteDTOByUsername("Davide");
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "UtenteNotFound - Utente non registrato!");
        }
    }

    @Test
    void testPostUtente() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);

        UtenteDTO utenteDTOcreated = utenteBL.postUtente(utenteDTO);

        assertEquals(utenteDTO.getUsername(), utenteDTOcreated.getUsername());
        assertEquals(utenteDTO.getPassword(), utenteDTOcreated.getPassword());
        assertEquals(utenteDTO.isStatoPagamento(), utenteDTOcreated.isStatoPagamento());
    }

    @Test
    void testPostUtenteFail() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);

        utenteBL.postUtente(utenteDTO);

        try {
            utenteBL.postUtente(utenteDTO);
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "UtenteAlreadySigned - Utente già registrato!");
        }
    }

    @Test
    void testPutUtente() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);

        UtenteDTO utenteDTOcreated = utenteBL.postUtente(utenteDTO);

        utenteDTO.setUsername("Matteo");

        UtenteDTO utenteDTOput = utenteBL.putUtente(utenteDTOcreated.getId(), utenteDTO);

        assertEquals(utenteDTO.getUsername(), utenteDTOput.getUsername());
        assertEquals(utenteDTO.getPassword(), utenteDTOput.getPassword());
        assertEquals(utenteDTO.isStatoPagamento(), utenteDTOput.isStatoPagamento());
    }

    @Test
    void testPutUtenteFail() {
        try {
            utenteBL.putUtente(1L, new UtenteDTO());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "UtenteNotFound - Utente non registrato!");
        }
    }

    @Test
    void testDeleteUtente() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);

        UtenteDTO utenteDTOcreated = utenteBL.postUtente(utenteDTO);

        utenteBL.getUtenteDTObyId(utenteDTOcreated.getId());

        utenteBL.deleteUtente(utenteDTOcreated.getId());

        try {
            utenteBL.getUtenteDTObyId(utenteDTOcreated.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "UtenteNotFound - Utente non registrato!");
        }
    }

    /*
    IL TEST SUL PAGAMENTO NON E' REALIZZABILE, DATO CHE IL COMPORTAMENTO E' RANDOMICO!
     */

    //SEZIONE MAPPER

    @Test
    void testMapperUtente() {
        Utente utente = creaUtenteModel(1L, "Davide", "1234", false);

        UtenteDTO utenteDTO = utenteMapper.utenteToUtenteDTO(utente);

        //Testing Model -> DTO
        assertEquals(utente.getUsername(), utenteDTO.getUsername());
        assertEquals(utente.getPassword(), utenteDTO.getPassword());
        assertEquals(utente.getStatoPagamento(), utenteDTO.isStatoPagamento());

        Utente utente1 = utenteMapper.utenteDTOToUtente(utenteDTO);

        //Testing DTO -> Model
        assertEquals(utenteDTO.getUsername(), utente1.getUsername());
        assertEquals(utenteDTO.getPassword(), utente1.getPassword());
        assertEquals(utenteDTO.isStatoPagamento(), utente1.getStatoPagamento());

        //Testing Model -> Model
        assertEquals(utente.getUsername(), utente1.getUsername());
        assertEquals(utente.getPassword(), utente1.getPassword());
        assertEquals(utente.getStatoPagamento(), utente1.getStatoPagamento());
    }

    @Test
    void testMapperUtenteEntity() {
        UtenteEntity utente = creaUtenteEntity(1L, "Davide", "1234", false);

        UtenteDTO utenteDTO = utenteMapper.utenteEntityToUtenteDTO(utente);

        //Testing Entity -> DTO
        assertEquals(utente.getUsername(), utenteDTO.getUsername());
        assertEquals(utente.getPassword(), utenteDTO.getPassword());
        assertEquals(utente.isStatoPagamento(), utenteDTO.isStatoPagamento());

        UtenteEntity utente1 = utenteMapper.utenteDTOToUtenteEntity(utenteDTO);

        //Testing DTO -> Entity
        assertEquals(utenteDTO.getUsername(), utente1.getUsername());
        assertEquals(utenteDTO.getPassword(), utente1.getPassword());
        assertEquals(utenteDTO.isStatoPagamento(), utente1.isStatoPagamento());

        //Testing Entity -> Entity
        assertEquals(utente.getUsername(), utente1.getUsername());
        assertEquals(utente.getPassword(), utente1.getPassword());
        assertEquals(utente.isStatoPagamento(), utente1.isStatoPagamento());
    }

    @Test
    void testMapperUtenteNull() {
        Utente utente = null;

        UtenteDTO utenteDTO = utenteMapper.utenteToUtenteDTO(utente);

        assertNull(utenteDTO);

        utente = utenteMapper.utenteDTOToUtente(utenteDTO);

        assertNull(utente);
    }

    @Test
    void testMapperUtenteEntityNull() {
        UtenteEntity utenteEntity = null;

        UtenteDTO utenteDTO = utenteMapper.utenteEntityToUtenteDTO(utenteEntity);

        assertNull(utenteDTO);

        utenteEntity = utenteMapper.utenteDTOToUtenteEntity(utenteDTO);

        assertNull(utenteEntity);
    }
}
