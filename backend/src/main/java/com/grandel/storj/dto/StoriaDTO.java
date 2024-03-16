package com.grandel.storj.dto;

import com.grandel.storj.entity.UtenteEntity;

public class StoriaDTO {

    private Long id;

    private UtenteEntity idCreatore;

    private String titolo;

    private String categoria;

    private Integer numeroScenari;

    private Boolean statoCompletamento = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UtenteEntity getIdCreatore() {
        return idCreatore;
    }

    public void setIdCreatore(UtenteEntity idCreatore) {
        this.idCreatore = idCreatore;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getNumeroScenari() {
        return numeroScenari;
    }

    public void setNumeroScenari(Integer numeroScenari) {
        this.numeroScenari = numeroScenari;
    }

    public Boolean getStatoCompletamento() {
        return statoCompletamento;
    }

    public void setStatoCompletamento(Boolean statoCompletamento) {
        this.statoCompletamento = statoCompletamento;
    }
}
