package com.grandel.storj.service;

import com.grandel.storj.entity.PartitaEntity;
import com.grandel.storj.repository.PartitaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PartitaService {

    @Autowired
    private PartitaRepository partitaRepository;

    public Optional<PartitaEntity> findById(Long id) {
        return partitaRepository.findById(id);
    }

    public PartitaEntity postPartita(PartitaEntity partitaEntity) {
        return partitaRepository.save(partitaEntity);
    }

    public PartitaEntity putPartita(PartitaEntity partitaEntity) {
        return partitaRepository.save(partitaEntity);
    }

    public void deletePartita(Long id) {
        partitaRepository.deleteById(id);
    }

    public List<PartitaEntity> getPartiteByUtente(Long idUtente) {
        return partitaRepository.getPartiteByUtente(idUtente);
    }
}
