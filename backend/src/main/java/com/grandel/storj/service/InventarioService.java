package com.grandel.storj.service;

import com.grandel.storj.entity.InventarioEntity;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.repository.InventarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class InventarioService {
    @Autowired
    private InventarioRepository inventarioRepository;

    public Optional<InventarioEntity> findById(Long id){
        return inventarioRepository.findById(id);
    }

    public InventarioEntity postInventario(InventarioEntity inventarioEntity){
        return inventarioRepository.save(inventarioEntity);
    }

    public InventarioEntity putInventario(InventarioEntity inventarioEntity){
        return inventarioRepository.save(inventarioEntity);
    }

    public void deleteInventario(Long id){
        inventarioRepository.deleteById(id);
    }

    public List<OggettoEntity> getOggettiByPartita(Long idPartita){
        return inventarioRepository.getOggettiByPartita(idPartita);
    }
}
