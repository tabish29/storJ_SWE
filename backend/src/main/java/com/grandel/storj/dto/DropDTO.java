package com.grandel.storj.dto;

import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.ScenarioEntity;

public class DropDTO {

    private Long id;

    private ScenarioEntity idScenario;

    private OggettoEntity idOggetto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScenarioEntity getIdScenario() {
        return idScenario;
    }

    public void setIdScenario(ScenarioEntity idScenario) {
        this.idScenario = idScenario;
    }

    public OggettoEntity getIdOggetto() {
        return idOggetto;
    }

    public void setIdOggetto(OggettoEntity idOggetto) {
        this.idOggetto = idOggetto;
    }
}
