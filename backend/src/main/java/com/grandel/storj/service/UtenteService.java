package com.grandel.storj.service;

import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public List<UtenteEntity> findByUsername(String username){
        return utenteRepository.findByUsername(username);
    }

    public UtenteEntity saveUtente(UtenteEntity utenteEntity){
        return utenteRepository.save(utenteEntity);
    }
}
