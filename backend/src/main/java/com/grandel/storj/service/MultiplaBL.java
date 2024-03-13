package com.grandel.storj.service;

import com.grandel.storj.dto.MultiplaDTO;
import com.grandel.storj.entity.MultiplaEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.MultiplaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MultiplaBL {
    @Autowired
    private MultiplaMapper multiplaMapper;
    @Autowired
    private MultiplaService multiplaService;

    public MultiplaDTO getMultiplaDTOById(Long id){
        Optional<MultiplaEntity> multipla = multiplaService.findById(id);
        if(!multipla.isPresent()){
            throw new ErrorException(ErrorEnum.MULTIPLANOTFOUND);
        }

        return multiplaMapper.multiplaEntityToMultiplaDTO(multipla.get());
    }

    public MultiplaDTO postMultipla(MultiplaDTO multiplaDTO){
        MultiplaEntity multiplaEntity = multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO);
        multiplaEntity = multiplaService.postMultipla(multiplaEntity);
        return multiplaMapper.multiplaEntityToMultiplaDTO(multiplaEntity);
    }

    public MultiplaDTO putMultipla(Long id, MultiplaDTO multiplaDTO){
        Optional<MultiplaEntity> multipla = multiplaService.findById(id);
        if(!multipla.isPresent()){
            throw new ErrorException(ErrorEnum.MULTIPLANOTFOUND);
        }

        multiplaDTO.setId(id);
        MultiplaEntity multiplaEntity = multiplaMapper.multiplaDTOToMultiplaEntity(multiplaDTO);
        multiplaEntity = multiplaService.putMultipla(multiplaEntity);

        return multiplaMapper.multiplaEntityToMultiplaDTO(multiplaEntity);
    }

    public void deleteMultipla(Long id){
        if(getMultiplaDTOById(id) != null){
            multiplaService.deleteMultipla(id);
        }
    }

    public List<MultiplaDTO> getMultipleByScenario(Long idScenario){
        List<MultiplaDTO> multiple = new ArrayList<>();

        for (MultiplaEntity x : multiplaService.findByScenario(idScenario)) {
            multiple.add(multiplaMapper.multiplaEntityToMultiplaDTO(x));
        }

        return multiple;
    }
}
