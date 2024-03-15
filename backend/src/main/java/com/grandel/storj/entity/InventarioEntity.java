package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "inventario")
public class InventarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_partita", nullable = false)
    private PartitaEntity idPartita;

    @ManyToOne
    @JoinColumn(name = "id_oggetto", nullable = false)
    private OggettoEntity idOggetto;
}
