package com.grandel.storj.service;

import com.grandel.storj.dto.RequiredDTO;
import com.grandel.storj.entity.RequiredEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.RequiredMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class RequiredBL {

    @Autowired
    private RequiredMapper requiredMapper;
    @Autowired
    private RequiredService requiredService;

    public RequiredDTO getRequiredDTOById(Long id) {
        Optional<RequiredEntity> required = requiredService.findById(id);
        if (!required.isPresent()) {
            throw new ErrorException(ErrorEnum.REQUIREDNOTFOUND);
        }

        return requiredMapper.requiredEntityToRequiredDTO(required.get());
    }

    public RequiredDTO postRequired(RequiredDTO requiredDTO) {
        RequiredEntity requiredEntity = requiredMapper.requiredDTOToRequiredEntity(requiredDTO);
        requiredEntity = requiredService.postRequired(requiredEntity);
        return requiredMapper.requiredEntityToRequiredDTO(requiredEntity);
    }

    public RequiredDTO putRequired(Long id, RequiredDTO requiredDTO) {
        Optional<RequiredEntity> required = requiredService.findById(id);
        if (!required.isPresent()) {
            throw new ErrorException(ErrorEnum.REQUIREDNOTFOUND);
        }

        requiredDTO.setId(id);
        RequiredEntity requiredEntity = requiredMapper.requiredDTOToRequiredEntity(requiredDTO);
        requiredEntity = requiredService.putRequired(requiredEntity);

        return requiredMapper.requiredEntityToRequiredDTO(requiredEntity);
    }

    public void deleteRequired(Long id) {
        if (getRequiredDTOById(id) != null) {
            requiredService.deleteRequired(id);
        }
    }

    public RequiredDTO getRequiredByScelta(Long idScelta) {
        RequiredEntity requiredEntity = requiredService.getRequiredByScelta(idScelta);
        return requiredMapper.requiredEntityToRequiredDTO(requiredEntity);
    }
}
