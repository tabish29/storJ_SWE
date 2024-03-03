package com.grandel.storj.service;

import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.mapper.UtenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteBL {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteMapper utenteMapper;

    public UtenteDTO getUtenteDTOByUsername(String username){
        List<UtenteEntity> list = utenteService.findByUsername(username);

        if(list.isEmpty() || list.size() > 1){
            throw new RuntimeException();
        }

        return utenteMapper.utenteEntityToUtenteDTO(list.get(0));
    }

    public UtenteDTO postUtente(UtenteDTO utenteDTO){
        UtenteEntity utenteEntity = utenteMapper.utenteDTOToUtenteEntity(utenteDTO);
        utenteEntity = utenteService.saveUtente(utenteEntity);
        return utenteMapper.utenteEntityToUtenteDTO(utenteEntity);
    }
}
