package com.grandel.storj.mapper;

import com.grandel.storj.dto.ScenarioDTO;
import com.grandel.storj.dto.StoriaDTO;
import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.repository.UtenteRepository;
import com.grandel.storj.service.ScenarioBL;
import com.grandel.storj.service.UtenteBL;
import com.grandel.storj.service.UtenteService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Storia;

@Mapper(componentModel = "spring")
public abstract class StoriaMapper {
    @Autowired
    UtenteMapper utenteMapper;
    @Autowired
    UtenteBL utenteBL;

    public abstract StoriaDTO storiaToStoriaDTO(Storia storia);

    public abstract StoriaDTO storiaEntityToStoriaDTO(StoriaEntity storiaEntity);

    public abstract StoriaEntity storiaDTOToStoriaEntity(StoriaDTO storiaDTO);

    public abstract Storia storiaDTOToStoria(StoriaDTO storiaDTO);

    public UtenteEntity mapU(Long id){
        UtenteDTO utenteDTO = utenteBL.getUtenteDTObyId(id);
        return utenteMapper.utenteDTOToUtenteEntity(utenteDTO);
    }

    public Long mapU(UtenteEntity utenteEntity){
        return utenteEntity.getId();
    }
}
