package com.grandel.storj.service;

import com.grandel.storj.dto.IndovinelloDTO;
import com.grandel.storj.entity.IndovinelloEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.IndovinelloMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IndovinelloBL {

    @Autowired
    private IndovinelloMapper indovinelloMapper;
    @Autowired
    private IndovinelloService indovinelloService;

    public IndovinelloDTO getIndovinelloDTOById(Long id) {
        Optional<IndovinelloEntity> indovinello = indovinelloService.findById(id);
        if (!indovinello.isPresent()) {
            throw new ErrorException(ErrorEnum.INDOVINELLONOTFOUND);
        }

        return indovinelloMapper.indovinelloEntityToIndovinelloDTO(indovinello.get());
    }

    public IndovinelloDTO postIndovinello(IndovinelloDTO indovinelloDTO) {
        IndovinelloEntity indovinelloEntity = indovinelloMapper.indovinelloDTOToIndovinelloEntity(indovinelloDTO);
        indovinelloEntity = indovinelloService.postIndovinello(indovinelloEntity);
        return indovinelloMapper.indovinelloEntityToIndovinelloDTO(indovinelloEntity);
    }

    public IndovinelloDTO putIndovinello(Long id, IndovinelloDTO indovinelloDTO) {
        Optional<IndovinelloEntity> indovinello = indovinelloService.findById(id);
        if (!indovinello.isPresent()) {
            throw new ErrorException(ErrorEnum.INDOVINELLONOTFOUND);
        }

        indovinelloDTO.setId(id);
        IndovinelloEntity indovinelloEntity = indovinelloMapper.indovinelloDTOToIndovinelloEntity(indovinelloDTO);
        indovinelloEntity = indovinelloService.putIndovinello(indovinelloEntity);

        return indovinelloMapper.indovinelloEntityToIndovinelloDTO(indovinelloEntity);
    }

    public void deleteIndovinello(Long id) {
        if (getIndovinelloDTOById(id) != null) {
            indovinelloService.deleteIndovinello(id);
        }
    }

    public IndovinelloDTO getIndovinelloByScenario(Long idScenario) {
        IndovinelloEntity indovinelloEntity = indovinelloService.findByScenario(idScenario);
        return indovinelloMapper.indovinelloEntityToIndovinelloDTO(indovinelloEntity);
    }
}
