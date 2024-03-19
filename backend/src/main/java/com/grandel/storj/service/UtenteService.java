package com.grandel.storj.service;

import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.repository.UtenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Optional<UtenteEntity> findById(Long id){
        return utenteRepository.findById(id);
    }
    public List<UtenteEntity> findByUsername(String username){
        return utenteRepository.findByUsername(username);
    }

    public UtenteEntity saveUtente(UtenteEntity utenteEntity){
        return utenteRepository.save(utenteEntity);
    }

    public UtenteEntity putUtente(UtenteEntity utenteEntity){
        return utenteRepository.save(utenteEntity);
    }

    public void deleteUtente(Long id){
        utenteRepository.deleteById(id);
    }
}
