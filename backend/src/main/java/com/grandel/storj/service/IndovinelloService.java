package com.grandel.storj.service;

import com.grandel.storj.entity.IndovinelloEntity;
import com.grandel.storj.repository.IndovinelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IndovinelloService {
    @Autowired
    private IndovinelloRepository indovinelloRepository;

    public Optional<IndovinelloEntity> findById(Long id){
        return indovinelloRepository.findById(id);
    }

    public IndovinelloEntity postIndovinello(IndovinelloEntity indovinelloEntity){
        return indovinelloRepository.save(indovinelloEntity);
    }

    public IndovinelloEntity putIndovinello(IndovinelloEntity indovinelloEntity){
        return indovinelloRepository.save(indovinelloEntity);
    }

    public void deleteIndovinello(Long id){
        indovinelloRepository.deleteById(id);
    }

    public IndovinelloEntity findByScenario(Long idScenario){
        return indovinelloRepository.findByScenario(idScenario);
    }
}
