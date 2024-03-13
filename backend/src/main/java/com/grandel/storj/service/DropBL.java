package com.grandel.storj.service;

import com.grandel.storj.dto.DropDTO;
import com.grandel.storj.entity.DropEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.DropMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class DropBL {
    @Autowired
    private DropMapper dropMapper;
    @Autowired
    private DropService dropService;

    public DropDTO getDropDTOById(Long id){
        Optional<DropEntity> drop = dropService.findById(id);
        if(!drop.isPresent()){
            throw new ErrorException(ErrorEnum.DROPNOTFOUND);
        }

        return dropMapper.dropEntityToDropDTO(drop.get());
    }

    public DropDTO postDrop(DropDTO dropDTO){
        DropEntity dropEntity = dropMapper.dropDTOToDropEntity(dropDTO);
        dropEntity = dropService.postDrop(dropEntity);
        return dropMapper.dropEntityToDropDTO(dropEntity);
    }

    public DropDTO putDrop(Long id, DropDTO dropDTO){
        Optional<DropEntity> drop = dropService.findById(id);
        if(!drop.isPresent()){
            throw new ErrorException(ErrorEnum.DROPNOTFOUND);
        }

        dropDTO.setId(id);
        DropEntity dropEntity = dropMapper.dropDTOToDropEntity(dropDTO);
        dropEntity = dropService.putDrop(dropEntity);

        return dropMapper.dropEntityToDropDTO(dropEntity);
    }

    public void deleteDrop(Long id){
        if(getDropDTOById(id) != null){
            dropService.deleteDrop(id);
        }
    }

    public DropDTO getDropByScenario(Long idScenario){
        DropEntity dropEntity = dropService.getDropByScenario(idScenario);
        return dropMapper.dropEntityToDropDTO(dropEntity);
    }
}
