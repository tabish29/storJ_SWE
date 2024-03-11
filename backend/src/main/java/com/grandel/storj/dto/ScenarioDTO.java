package com.grandel.storj.dto;

import com.grandel.storj.entity.OggettoEntity;
import com.grandel.storj.entity.StoriaEntity;
import storj.model.TipoRispostaEnum;
import storj.model.TipoScenarioEnum;

public class ScenarioDTO {
    private Long id;
    private StoriaEntity idStoria;
    private OggettoEntity idOggettoDroppato;
    private String testo;
    private TipoRispostaEnum tipoRisposta;
    private TipoScenarioEnum tipoScenario;

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

    public OggettoEntity getIdOggettoDroppato() {
        return idOggettoDroppato;
    }

    public void setIdOggettoDroppato(OggettoEntity idOggettoDroppato) {
        this.idOggettoDroppato = idOggettoDroppato;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public TipoRispostaEnum getTipoRisposta() {
        return tipoRisposta;
    }

    public void setTipoRisposta(TipoRispostaEnum tipoRisposta) {
        this.tipoRisposta = tipoRisposta;
    }

    public TipoScenarioEnum getTipoScenario() {
        return tipoScenario;
    }

    public void setTipoScenario(TipoScenarioEnum tipoScenario) {
        this.tipoScenario = tipoScenario;
    }
}
