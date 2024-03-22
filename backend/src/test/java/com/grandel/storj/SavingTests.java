package com.grandel.storj;

import com.grandel.storj.dto.*;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SavingTests extends AbstractTests {

    @Autowired
    private SavingService savingService;
    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private UtenteBL utenteBL;
    @Autowired
    private MultiplaBL multiplaBL;
    @Autowired
    private IndovinelloBL indovinelloBL;

    @BeforeEach
    void drop() {
        super.drop();
    }

    @Test
    void testSaveStoria() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        ScenarioDTO scenarioDTO3 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo3", TipoRispostaEnum.INDOVINELLO, TipoScenarioEnum.NORMALE);
        scenarioDTO3 = scenarioBL.postScenario(scenarioDTO3);

        ScenarioDTO scenarioDTO4 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo4", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO4 = scenarioBL.postScenario(scenarioDTO4);

        ScenarioDTO scenarioDTO5 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo5", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO5 = scenarioBL.postScenario(scenarioDTO5);

        //SCELTE scenarioDTO
        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);
        MultiplaDTO multiplaDTO1 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));
        multiplaDTO1 = multiplaBL.postMultipla(multiplaDTO1);
        MultiplaDTO multiplaDTO2 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo2", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3));
        multiplaDTO2 = multiplaBL.postMultipla(multiplaDTO2);

        //SCELTE scenarioDTO1
        MultiplaDTO multiplaDTO3 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO3 = multiplaBL.postMultipla(multiplaDTO3);
        MultiplaDTO multiplaDTO4 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO4 = multiplaBL.postMultipla(multiplaDTO4);

        //SCELTE scenarioDTO2
        MultiplaDTO multiplaDTO5 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO5 = multiplaBL.postMultipla(multiplaDTO5);
        MultiplaDTO multiplaDTO6 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO6 = multiplaBL.postMultipla(multiplaDTO6);

        //SCELTA scenarioDTO3
        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        indovinelloDTO = indovinelloBL.postIndovinello(indovinelloDTO);

        savingService.saveStoria(storiaDTO.getId());
    }

    @Test
    void testSaveStoriaFail() {
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

        ScenarioDTO scenarioDTO3 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo3", TipoRispostaEnum.INDOVINELLO, TipoScenarioEnum.NORMALE);
        scenarioDTO3 = scenarioBL.postScenario(scenarioDTO3);

        ScenarioDTO scenarioDTO4 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo4", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO4 = scenarioBL.postScenario(scenarioDTO4);

        ScenarioDTO scenarioDTO5 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo5", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO5 = scenarioBL.postScenario(scenarioDTO5);

        //SCELTE scenarioDTO
        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);
        MultiplaDTO multiplaDTO1 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));
        multiplaDTO1 = multiplaBL.postMultipla(multiplaDTO1);
        MultiplaDTO multiplaDTO2 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo2", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3));
        multiplaDTO2 = multiplaBL.postMultipla(multiplaDTO2);

        //SCELTE scenarioDTO1
        MultiplaDTO multiplaDTO3 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO3 = multiplaBL.postMultipla(multiplaDTO3);
        MultiplaDTO multiplaDTO4 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO4 = multiplaBL.postMultipla(multiplaDTO4);

        //SCELTE scenarioDTO2
        MultiplaDTO multiplaDTO5 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO5 = multiplaBL.postMultipla(multiplaDTO5);
        MultiplaDTO multiplaDTO6 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO6 = multiplaBL.postMultipla(multiplaDTO6);

        //SCELTA scenarioDTO3
        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        indovinelloDTO = indovinelloBL.postIndovinello(indovinelloDTO);

        try {
            savingService.saveStoria(storiaDTO.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "SalvataggioFailedScenarioIniziale - Salvataggio storia fallito. Non sono presenti scenari iniziali, o ne è presente più di uno!");
        }
    }

    @Test
    void testSaveStoriaFail2() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        ScenarioDTO scenarioDTO3 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo3", TipoRispostaEnum.INDOVINELLO, TipoScenarioEnum.NORMALE);
        scenarioDTO3 = scenarioBL.postScenario(scenarioDTO3);

        ScenarioDTO scenarioDTO4 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo4", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO4 = scenarioBL.postScenario(scenarioDTO4);

        ScenarioDTO scenarioDTO5 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo5", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO5 = scenarioBL.postScenario(scenarioDTO5);

        //SCELTE scenarioDTO
        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);
        MultiplaDTO multiplaDTO1 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));
        multiplaDTO1 = multiplaBL.postMultipla(multiplaDTO1);
        MultiplaDTO multiplaDTO2 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo2", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3));
        multiplaDTO2 = multiplaBL.postMultipla(multiplaDTO2);

        //SCELTE scenarioDTO1
        MultiplaDTO multiplaDTO3 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO3 = multiplaBL.postMultipla(multiplaDTO3);
        MultiplaDTO multiplaDTO4 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO4 = multiplaBL.postMultipla(multiplaDTO4);

        //SCELTE scenarioDTO2
        MultiplaDTO multiplaDTO5 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO5 = multiplaBL.postMultipla(multiplaDTO5);
        MultiplaDTO multiplaDTO6 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO6 = multiplaBL.postMultipla(multiplaDTO6);

        //SCELTA scenarioDTO3
        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        indovinelloDTO = indovinelloBL.postIndovinello(indovinelloDTO);

        try {
            savingService.saveStoria(storiaDTO.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "SalvataggioFailedScenarioFinale - Salvataggio storia fallito. Devono esserci degli scenari finali!");
        }
    }

    @Test
    void testSaveStoriaFail3() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        ScenarioDTO scenarioDTO3 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo3", TipoRispostaEnum.INDOVINELLO, TipoScenarioEnum.NORMALE);
        scenarioDTO3 = scenarioBL.postScenario(scenarioDTO3);

        ScenarioDTO scenarioDTO4 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo4", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO4 = scenarioBL.postScenario(scenarioDTO4);

        ScenarioDTO scenarioDTO5 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo5", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO5 = scenarioBL.postScenario(scenarioDTO5);

        //SCELTE scenarioDTO
        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);
        MultiplaDTO multiplaDTO1 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));
        multiplaDTO1 = multiplaBL.postMultipla(multiplaDTO1);
        MultiplaDTO multiplaDTO2 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo2", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3));
        multiplaDTO2 = multiplaBL.postMultipla(multiplaDTO2);

        //SCELTE scenarioDTO1
        MultiplaDTO multiplaDTO3 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO3 = multiplaBL.postMultipla(multiplaDTO3);

        //SCELTE scenarioDTO2
        MultiplaDTO multiplaDTO5 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO5 = multiplaBL.postMultipla(multiplaDTO5);
        MultiplaDTO multiplaDTO6 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO6 = multiplaBL.postMultipla(multiplaDTO6);

        //SCELTA scenarioDTO3
        IndovinelloDTO indovinelloDTO = creaIndovinelloDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3), "testo", "risposta", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4), scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        indovinelloDTO = indovinelloBL.postIndovinello(indovinelloDTO);

        try {
            savingService.saveStoria(storiaDTO.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "SalvataggioFailedMultipla - Salvataggio storia fallito. In ogni scenario multiplo devono essere presenti almeno due scelte!");
        }
    }

    @Test
    void testSaveStoriaFail4() {
        UtenteDTO utenteDTO = creaUtenteDTO("Davide", "1234", false);
        utenteDTO = utenteBL.postUtente(utenteDTO);

        StoriaDTO storiaDTO = creaStoriaDTO(utenteMapper.utenteDTOToUtenteEntity(utenteDTO), "titolo", "categoria", 0, false);
        storiaDTO = storiaBL.postStoria(storiaDTO);

        ScenarioDTO scenarioDTO = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.INIZIALE);
        scenarioDTO = scenarioBL.postScenario(scenarioDTO);

        ScenarioDTO scenarioDTO1 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo1", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO1 = scenarioBL.postScenario(scenarioDTO1);

        ScenarioDTO scenarioDTO2 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo2", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.NORMALE);
        scenarioDTO2 = scenarioBL.postScenario(scenarioDTO2);

        ScenarioDTO scenarioDTO3 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo3", TipoRispostaEnum.INDOVINELLO, TipoScenarioEnum.NORMALE);
        scenarioDTO3 = scenarioBL.postScenario(scenarioDTO3);

        ScenarioDTO scenarioDTO4 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo4", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO4 = scenarioBL.postScenario(scenarioDTO4);

        ScenarioDTO scenarioDTO5 = creaScenarioDTO(storiaMapper.storiaDTOToStoriaEntity(storiaDTO), "testo5", TipoRispostaEnum.MULTIPLA, TipoScenarioEnum.FINALE);
        scenarioDTO5 = scenarioBL.postScenario(scenarioDTO5);

        //SCELTE scenarioDTO
        MultiplaDTO multiplaDTO = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1));
        multiplaDTO = multiplaBL.postMultipla(multiplaDTO);
        MultiplaDTO multiplaDTO1 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2));
        multiplaDTO1 = multiplaBL.postMultipla(multiplaDTO1);
        MultiplaDTO multiplaDTO2 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO), "testo2", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO3));
        multiplaDTO2 = multiplaBL.postMultipla(multiplaDTO2);

        //SCELTE scenarioDTO1
        MultiplaDTO multiplaDTO3 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO3 = multiplaBL.postMultipla(multiplaDTO3);
        MultiplaDTO multiplaDTO4 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO1), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO4 = multiplaBL.postMultipla(multiplaDTO4);

        //SCELTE scenarioDTO2
        MultiplaDTO multiplaDTO5 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO4));
        multiplaDTO5 = multiplaBL.postMultipla(multiplaDTO5);
        MultiplaDTO multiplaDTO6 = creaMultiplaDTO(scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO2), "testo1", scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO5));
        multiplaDTO6 = multiplaBL.postMultipla(multiplaDTO6);

        try {
            savingService.saveStoria(storiaDTO.getId());
            fail();
        } catch (ErrorException e) {
            assertEquals(e.getMessage(), "SalvataggioFailedIndovinello - Salvataggio storia fallito. In ogni scenario indovinello deve essere presente una scelta!");
        }
    }
}
