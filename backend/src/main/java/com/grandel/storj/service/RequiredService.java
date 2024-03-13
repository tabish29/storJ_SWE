package com.grandel.storj.service;

import com.grandel.storj.entity.RequiredEntity;
import com.grandel.storj.repository.RequiredRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RequiredService {
    @Autowired
    private RequiredRepository requiredRepository;

    public Optional<RequiredEntity> findById(Long id){
        return requiredRepository.findById(id);
    }

    public RequiredEntity postRequired(RequiredEntity requiredEntity){
        return requiredRepository.save(requiredEntity);
    }

    public RequiredEntity putRequired(RequiredEntity requiredEntity){
        return requiredRepository.save(requiredEntity);
    }

    public void deleteRequired(Long id){
        requiredRepository.deleteById(id);
    }

    public RequiredEntity getRequiredByScelta(Long idScelta){
        return requiredRepository.getRequiredByScelta(idScelta);
    }
}
