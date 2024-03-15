package com.grandel.storj.mapper;

import com.grandel.storj.dto.InventarioDTO;
import com.grandel.storj.dto.OggettoDTO;
import com.grandel.storj.dto.PartitaDTO;
import com.grandel.storj.entity.InventarioEntity;
import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.PartitaEntity;
import com.grandel.storj.service.OggettoBL;
import com.grandel.storj.service.PartitaBL;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import storj.model.Inventario;

@Mapper(componentModel = "spring")
public abstract class InventarioMapper {
    @Autowired
    OggettoMapper oggettoMapper;
    @Autowired
    OggettoBL oggettoBL;
    @Autowired
    PartitaMapper partitaMapper;
    @Autowired
    PartitaBL partitaBL;

    public abstract InventarioDTO inventarioToInventarioDTO(Inventario inventario);
    public abstract InventarioDTO inventarioEntityToInventarioDTO(InventarioEntity inventarioEntity);
    public abstract Inventario inventarioDTOToInventario(InventarioDTO inventarioDTO);
    public abstract InventarioEntity inventarioDTOTOInventarioEntity(InventarioDTO inventarioDTO);

    public PartitaEntity mapP(Long id){
        PartitaDTO partitaDTO = partitaBL.getPartitaDTOById(id);
        return partitaMapper.partitaDTOToPartitaEntity(partitaDTO);
    }

    public Long mapP(PartitaEntity partitaEntity){
        return partitaEntity.getId();
    }

    public OggettoEntity mapO(Long id){
        OggettoDTO oggettoDTO = oggettoBL.getOggettoDTOById(id);
        return oggettoMapper.oggettoDTOToOggettoEntity(oggettoDTO);
    }

    public Long mapO(OggettoEntity oggettoEntity){
        return oggettoEntity.getId();
    }
}
