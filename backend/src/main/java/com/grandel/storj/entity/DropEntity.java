package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "drop")
public class DropEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_scenario", nullable = false)
    private ScenarioEntity idScenario;

    @ManyToOne
    @JoinColumn(name = "id_oggetto", nullable = false)
    private OggettoEntity idOggetto;
}
