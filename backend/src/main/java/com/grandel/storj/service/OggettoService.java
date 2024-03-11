package com.grandel.storj.service;

import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.repository.OggettoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OggettoService {
    @Autowired
    private OggettoRepository oggettoRepository;

    public OggettoEntity postOggetto(OggettoEntity oggettoEntity){
        return oggettoRepository.save(oggettoEntity);
    }

    public Optional<OggettoEntity> findById(Long id){
        return oggettoRepository.findById(id);
    }

    public OggettoEntity putOggetto(OggettoEntity oggettoEntity){
        return oggettoRepository.save(oggettoEntity);
    }

    public void deleteOggetto(Long id){
        oggettoRepository.deleteById(id);
    }
}
