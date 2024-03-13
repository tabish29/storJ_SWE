package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "required")
public class RequiredEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_scelta", nullable = false)
    private MultiplaEntity idScelta;

    @ManyToOne
    @JoinColumn(name = "id_oggetto", nullable = false)
    private OggettoEntity idOggetto;
}
