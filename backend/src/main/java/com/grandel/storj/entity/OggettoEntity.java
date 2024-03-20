package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "oggetto")
public class OggettoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_storia", nullable = false)
    private StoriaEntity idStoria;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descrizione")
    private String descrizione;
}
