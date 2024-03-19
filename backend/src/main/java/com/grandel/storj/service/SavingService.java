package com.grandel.storj.service;

import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

import java.util.List;

@Slf4j
@Service
public class SavingService {
    @Autowired
    private MultiplaBL multiplaBL;
    @Autowired
    private ScenarioBL scenarioBL;
    @Autowired
    private StoriaBL storiaBL;
    @Autowired
    private IndovinelloBL indovinelloBL;

    public void saveStoria(Long id) {
        if (scenarioBL.getScenariByStoria(id, "Iniziale").size() != 1) {
            throw new ErrorException(ErrorEnum.SALVATAGGIOFAILEDSCIN);
        } else if (scenarioBL.getScenariByStoria(id, "Finale").size() < 1) {
            throw new ErrorException(ErrorEnum.SALVATAGGIOFAILEDSCFI);
        }

        List<ScenarioDTO> scenari = scenarioBL.getScenariByStoria(id, null);

        for (ScenarioDTO x : scenari) {
            if(!x.getTipoScenario().equals(TipoScenarioEnum.FINALE)){
                if (x.getTipoRisposta().equals(TipoRispostaEnum.MULTIPLA)) {
                    if (multiplaBL.getMultipleByScenario(x.getId()).size() < 2) {
                        throw new ErrorException(ErrorEnum.SALVATAGGIOFAILEDMULT);
                    }
                } else {
                    if(indovinelloBL.getIndovinelloByScenario(x.getId()) == null){
                        throw new ErrorException(ErrorEnum.SALVATAGGIOFAILEDINDO);
                    }
                }
            }
        }

        StoriaDTO storiaDTO = storiaBL.getStoriaDTOById(id);
        storiaDTO.setStatoCompletamento(true);
        storiaBL.putStoria(id, storiaDTO);
    }
}
