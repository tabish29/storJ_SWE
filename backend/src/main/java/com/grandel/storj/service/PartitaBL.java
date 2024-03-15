package com.grandel.storj.service;

import com.grandel.storj.dto.PartitaDTO;
import com.grandel.storj.entity.PartitaEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.PartitaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PartitaBL {
    @Autowired
    private PartitaMapper partitaMapper;
    @Autowired
    private PartitaService partitaService;

    public PartitaDTO getPartitaDTOById(Long id){
        Optional<PartitaEntity> partita = partitaService.findById(id);
        if(!partita.isPresent()){
            throw new ErrorException(ErrorEnum.PARTITANOTFOUND);
        }

        return partitaMapper.partitaEntityToPartitaDTO(partita.get());
    }

    public PartitaDTO postPartita(PartitaDTO partitaDTO){
        PartitaEntity partitaEntity = partitaMapper.partitaDTOToPartitaEntity(partitaDTO);
        partitaEntity = partitaService.postPartita(partitaEntity);
        return partitaMapper.partitaEntityToPartitaDTO(partitaEntity);
    }

    public PartitaDTO putPartita(Long id, PartitaDTO partitaDTO){
        Optional<PartitaEntity> partita = partitaService.findById(id);
        if(!partita.isPresent()){
            throw new ErrorException(ErrorEnum.PARTITANOTFOUND);
        }

        partitaDTO.setId(id);
        PartitaEntity partitaEntity = partitaMapper.partitaDTOToPartitaEntity(partitaDTO);
        partitaEntity = partitaService.putPartita(partitaEntity);

        return partitaMapper.partitaEntityToPartitaDTO(partitaEntity);
    }

    public void deletePartita(Long id){
        if(getPartitaDTOById(id) != null){
            partitaService.deletePartita(id);
        }
    }

    public List<PartitaDTO> getPartiteByUtente(Long idUtente){
        List<PartitaDTO> partite = new ArrayList<>();

        for (PartitaEntity x : partitaService.getPartiteByUtente(idUtente)) {
            partite.add(partitaMapper.partitaEntityToPartitaDTO(x));
        }

        return partite;
    }
}
