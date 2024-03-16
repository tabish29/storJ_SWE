package com.grandel.storj.service;

import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.repository.StoriaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StoriaService {

    @Autowired
    private StoriaRepository storiaRepository;

    public StoriaEntity postStoria(StoriaEntity storiaEntity) {
        return storiaRepository.save(storiaEntity);
    }

    public List<StoriaEntity> getStorie() {
        return storiaRepository.findAll();
    }

    public List<StoriaEntity> getStorieFilterAutore(Long autore) {
        return storiaRepository.findByAutore(autore);
    }

    public List<StoriaEntity> getStorieFilterCategoria(String categoria) {
        return storiaRepository.findByCategoria(categoria);
    }

    public Optional<StoriaEntity> findById(Long id) {
        return storiaRepository.findById(id);
    }

    public StoriaEntity putStoria(StoriaEntity storiaEntity) {
        return storiaRepository.save(storiaEntity);
    }

    public void deleteStoria(Long id) {
        storiaRepository.deleteById(id);
    }
}
