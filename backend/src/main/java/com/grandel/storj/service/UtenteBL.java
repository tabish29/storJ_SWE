package com.grandel.storj.service;

import com.grandel.storj.dto.UtenteDTO;
import com.grandel.storj.entity.UtenteEntity;
import com.grandel.storj.error.ErrorEnum;
import com.grandel.storj.error.ErrorException;
import com.grandel.storj.mapper.UtenteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtenteBL {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private UtenteMapper utenteMapper;

    public UtenteDTO getUtenteDTOByUsername(String username){
        List<UtenteEntity> list = utenteService.findByUsername(username);

        if(list.isEmpty()){
            throw new ErrorException(ErrorEnum.UTENTENOTFOUND);
        }

        return utenteMapper.utenteEntityToUtenteDTO(list.get(0));
    }

    public UtenteDTO postUtente(UtenteDTO utenteDTO){
        if(!utenteService.findByUsername(utenteDTO.getUsername()).isEmpty()){
            throw new ErrorException(ErrorEnum.UTENTEALREADYSIGNED);
        }

        UtenteEntity utenteEntity = utenteMapper.utenteDTOToUtenteEntity(utenteDTO);
        utenteEntity = utenteService.saveUtente(utenteEntity);
        return utenteMapper.utenteEntityToUtenteDTO(utenteEntity);
    }

    public UtenteDTO utentePayment(String username){
        List<UtenteEntity> list = utenteService.findByUsername(username);

        if(list.isEmpty()){
            throw new ErrorException(ErrorEnum.UTENTENOTFOUND);
        }

        UtenteDTO utenteDTO = utenteMapper.utenteEntityToUtenteDTO(list.get(0));

        if(utenteDTO.isStatoPagamento()){
            throw new ErrorException(ErrorEnum.UTENTEALREADYPAID);
        }

        //Generazione numero randomico nell'intervallo [0,1], in modo da simulare il fallimento del pagamento
        int coin = (int)(Math.random()*2);
        if(coin == 0){
            throw new ErrorException(ErrorEnum.PAYMENTFAILED);
        }

        utenteDTO.setStatoPagamento(true);

        UtenteEntity utenteEntity = utenteMapper.utenteDTOToUtenteEntity(utenteDTO);
        utenteEntity = utenteService.saveUtente(utenteEntity);

        return utenteMapper.utenteEntityToUtenteDTO(utenteEntity);
    }

}
