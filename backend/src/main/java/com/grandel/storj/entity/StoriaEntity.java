package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "storia")
public class StoriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_creatore", nullable = false)
    private UtenteEntity idCreatore;

    @Column(name = "titolo")
    private String titolo;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "numero_scenari")
    private Integer numeroScenari = 0;

    @Column(name = "stato_completamento")
    private Boolean statoCompletamento = false;
}
