package com.grandel.storj.dto;

import com.grandel.storj.entity.MultiplaEntity;
import com.grandel.storj.entity.OggettoEntity;

public class RequiredDTO {

    private Long id;

    private MultiplaEntity idScelta;

    private OggettoEntity idOggetto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultiplaEntity getIdScelta() {
        return idScelta;
    }

    public void setIdScelta(MultiplaEntity idScelta) {
        this.idScelta = idScelta;
    }

    public OggettoEntity getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(OggettoEntity idOggetto) {
        this.idOggetto = idOggetto;
    }
}
