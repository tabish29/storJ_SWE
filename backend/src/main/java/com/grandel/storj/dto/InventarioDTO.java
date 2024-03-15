package com.grandel.storj.dto;

import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.PartitaEntity;

public class InventarioDTO {

    private Long id;

    private PartitaEntity idPartita;

    private OggettoEntity idOggetto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PartitaEntity getIdPartita() {
        return idPartita;
    }

    public void setIdPartita(PartitaEntity idPartita) {
        this.idPartita = idPartita;
    }

    public OggettoEntity getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(OggettoEntity idOggetto) {
        this.idOggetto = idOggetto;
    }
}
