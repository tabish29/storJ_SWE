package com.grandel.storj.mapper;

import com.grandel.storj.dto.PartitaDTO;
import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.PartitaEntity;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.service.ScenarioBL;
import com.grandel.storj.service.StoriaBL;
import com.grandel.storj.service.UtenteBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Partita;

@Mapper(componentModel = "spring")
public abstract class PartitaMapper {
    @Autowired
    StoriaMapper storiaMapper;
    @Autowired
    StoriaBL storiaBL;
    @Autowired
    UtenteMapper utenteMapper;
    @Autowired
    UtenteBL utenteBL;
    @Autowired
    ScenarioMapper scenarioMapper;
    @Autowired
    ScenarioBL scenarioBL;

    public abstract PartitaDTO partitaToPartitaDTO(Partita partita);
    public abstract PartitaDTO partitaEntityToPartitaDTO(PartitaEntity partitaEntity);
    public abstract Partita partitaDTOToPartita(PartitaDTO partitaDTO);
    public abstract PartitaEntity partitaDTOToPartitaEntity(PartitaDTO partitaDTO);

    public StoriaEntity mapSt(Long id){
        StoriaDTO storiaDTO = storiaBL.getStoriaDTOById(id);
        return storiaMapper.storiaDTOToStoriaEntity(storiaDTO);
    }

    public Long mapSt(StoriaEntity storiaEntity){
        return storiaEntity.getId();
    }

    public UtenteEntity mapU(Long id){
        UtenteDTO utenteDTO = utenteBL.getUtenteDTObyId(id);
        return utenteMapper.utenteDTOToUtenteEntity(utenteDTO);
    }

    public Long mapU(UtenteEntity utenteEntity){
        return utenteEntity.getId();
    }

    public ScenarioEntity mapSc(Long id){
        ScenarioDTO scenarioDTO = scenarioBL.getScenarioDTOById(id);
        return scenarioMapper.scenarioDTOToScenarioEntity(scenarioDTO);
    }

    public Long mapSc(ScenarioEntity scenarioEntity){
        return scenarioEntity.getId();
    }
}
