package com.grandel.storj.service;

import com.grandel.storj.entity.MultiplaEntity;
import com.grandel.storj.repository.MultiplaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MultiplaService {
    @Autowired
    private MultiplaRepository multiplaRepository;

    public Optional<MultiplaEntity> findById(Long id){
        return multiplaRepository.findById(id);
    }

    public MultiplaEntity postMultipla(MultiplaEntity multiplaEntity){
        return multiplaRepository.save(multiplaEntity);
    }

    public MultiplaEntity putMultipla(MultiplaEntity multiplaEntity){
        return multiplaRepository.save(multiplaEntity);
    }

    public void deleteMultipla(Long id){
        multiplaRepository.deleteById(id);
    }

    public List<MultiplaEntity> findByScenario(Long idScenario){
        return multiplaRepository.findByScenario(idScenario);
    }
}
