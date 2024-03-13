package com.grandel.storj.dto;

import com.grandel.storj.entity.ScenarioEntity;
import com.grandel.storj.entity.StoriaEntity;
import com.grandel.storj.entity.UtenteEntity;

public class PartitaDTO {

    private Long id;

    private StoriaEntity idStoria;

    private UtenteEntity idUtente;

    private ScenarioEntity idScenarioCorrente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StoriaEntity getIdStoria() {
        return idStoria;
    }

    public void setIdStoria(StoriaEntity idStoria) {
        this.idStoria = idStoria;
    }

    public UtenteEntity getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(UtenteEntity idUtente) {
        this.idUtente = idUtente;
    }

    public ScenarioEntity getIdScenarioCorrente() {
        return idScenarioCorrente;
    }

    public void setIdScenarioCorrente(ScenarioEntity idScenarioCorrente) {
        this.idScenarioCorrente = idScenarioCorrente;
    }
}
