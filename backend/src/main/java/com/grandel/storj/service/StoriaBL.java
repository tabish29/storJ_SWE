package com.grandel.storj.service;

import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.StoriaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class StoriaBL {
    @Autowired
    private StoriaService storiaService;
    @Autowired
    private StoriaMapper storiaMapper;
    @Autowired
    private UtenteBL utenteBL;

    public StoriaDTO getStoriaDTOById(Long id){
        Optional<StoriaEntity> storia = storiaService.findById(id);
        if(!storia.isPresent()){
            throw new ErrorException(ErrorEnum.STORIANOTFOUND);
        }

        return storiaMapper.storiaEntityToStoriaDTO(storia.get());
    }

    public StoriaDTO postStoria(StoriaDTO storiaDTO){
        StoriaEntity storiaEntity = storiaMapper.storiaDTOToStoriaEntity(storiaDTO);
        storiaEntity = storiaService.postStoria(storiaEntity);
        return storiaMapper.storiaEntityToStoriaDTO(storiaEntity);
    }

    public StoriaDTO putStoria(Long id, StoriaDTO storiaDTO){
        Optional<StoriaEntity> storia = storiaService.findById(id);
        if(!storia.isPresent()){
            throw new ErrorException(ErrorEnum.STORIANOTFOUND);
        }

        storiaDTO.setId(id);
        StoriaEntity storiaEntity = storiaMapper.storiaDTOToStoriaEntity(storiaDTO);
        storiaEntity = storiaService.putStoria(storiaEntity);

        return storiaMapper.storiaEntityToStoriaDTO(storiaEntity);
    }

    public void deleteStoria(Long id){
        if(getStoriaDTOById(id) != null){
            storiaService.deleteStoria(id);
        }
    }

    public List<StoriaDTO> getStorie(String autore, String categoria){
        if(autore == null && categoria == null){
            return findAll();
        } else if (autore != null && categoria == null) {
            UtenteDTO utenteDTO = utenteBL.getUtenteDTOByUsername(autore);
            return filterAutore(utenteDTO.getId());
        }else if (autore != null && categoria != null){
            throw new ErrorException(ErrorEnum.FILTROERROR);
        }else{
            return filterCategoria(categoria);
        }
    }

    private List<StoriaDTO> findAll(){
        List<StoriaEntity> storie = storiaService.getStorie();
        List<StoriaDTO> storieDTO = new ArrayList<>();

        for (StoriaEntity x : storie) {
            storieDTO.add(storiaMapper.storiaEntityToStoriaDTO(x));
        }

        return storieDTO;
    }

    private List<StoriaDTO> filterAutore(Long autore){
        List<StoriaEntity> storie = storiaService.getStorieFilterAutore(autore);
        List<StoriaDTO> storieDTO = new ArrayList<>();

        for (StoriaEntity x : storie) {
            storieDTO.add(storiaMapper.storiaEntityToStoriaDTO(x));
        }

        return storieDTO;
    }

    private List<StoriaDTO> filterCategoria(String categoria){
        List<StoriaEntity> storie = storiaService.getStorieFilterCategoria(categoria);
        List<StoriaDTO> storieDTO = new ArrayList<>();

        for (StoriaEntity x : storie) {
            storieDTO.add(storiaMapper.storiaEntityToStoriaDTO(x));
        }

        return storieDTO;
    }
}
