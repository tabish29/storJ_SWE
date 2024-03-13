package com.grandel.storj.dto;

import com.grandel.storj.entity.StoriaEntity;

public class OggettoDTO {
    private Long id;

    private StoriaEntity idStoria;

    private String nome;

    private String descrizione;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}
