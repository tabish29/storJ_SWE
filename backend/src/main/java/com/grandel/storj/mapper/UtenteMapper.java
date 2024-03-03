package com.grandel.storj.mapper;

import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.UtenteEntity;
import org.mapstruct.Mapper;
import storj.model.Utente;

@Mapper(componentModel = "spring")
public interface UtenteMapper {

    UtenteDTO utenteToUtenteDTO(Utente utente);

    UtenteEntity utenteDTOToUtenteEntity(UtenteDTO utenteDTO);

    Utente utenteDTOToUtente(UtenteDTO utenteDTO);

    UtenteDTO utenteEntityToUtenteDTO(UtenteEntity utenteEntity);
}
