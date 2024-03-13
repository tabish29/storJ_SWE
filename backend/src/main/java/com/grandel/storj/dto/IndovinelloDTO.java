package com.grandel.storj.dto;

import com.grandel.storj.entity.ScenarioEntity;

public class IndovinelloDTO {

    private Long id;

    private ScenarioEntity idScenario;

    private String testo;

    private String risposta;

    private ScenarioEntity idScenarioRispostaCorretta;

    private ScenarioEntity idScenarioRispostaSbagliata;

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

    public String getRisposta() {
        return risposta;
    }

    public void setRisposta(String risposta) {
        this.risposta = risposta;
    }

    public ScenarioEntity getIdScenarioRispostaCorretta() {
        return idScenarioRispostaCorretta;
    }

    public void setIdScenarioRispostaCorretta(ScenarioEntity idScenarioRispostaCorretta) {
        this.idScenarioRispostaCorretta = idScenarioRispostaCorretta;
    }

    public ScenarioEntity getIdScenarioRispostaSbagliata() {
        return idScenarioRispostaSbagliata;
    }

    public void setIdScenarioRispostaSbagliata(ScenarioEntity idScenarioRispostaSbagliata) {
        this.idScenarioRispostaSbagliata = idScenarioRispostaSbagliata;
    }
}
