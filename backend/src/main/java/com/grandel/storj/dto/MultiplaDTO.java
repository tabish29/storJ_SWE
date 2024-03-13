package com.grandel.storj.dto;

import com.grandel.storj.entity.ScenarioEntity;

public class MultiplaDTO {

    private Long id;

    private ScenarioEntity idScenario;

    private String testo;

    private ScenarioEntity idScenarioSuccessivo;

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

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public ScenarioEntity getIdScenarioSuccessivo() {
        return idScenarioSuccessivo;
    }

    public void setIdScenarioSuccessivo(ScenarioEntity idScenarioSuccessivo) {
        this.idScenarioSuccessivo = idScenarioSuccessivo;
    }
}
