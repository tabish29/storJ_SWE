package com.grandel.storj.service;

import com.grandel.storj.entity.DropEntity;
import com.grandel.storj.repository.DropRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DropService {

    @Autowired
    private DropRepository dropRepository;

    public Optional<DropEntity> findById(Long id) {
        return dropRepository.findById(id);
    }

    public DropEntity postDrop(DropEntity dropEntity) {
        return dropRepository.save(dropEntity);
    }

    public DropEntity putDrop(DropEntity dropEntity) {
        return dropRepository.save(dropEntity);
    }

    public void deleteDrop(Long id) {
        dropRepository.deleteById(id);
    }

    public DropEntity getDropByScenario(Long idScenario) {
        return dropRepository.getDropByScenario(idScenario);
    }
}
