package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "multipla")
public class MultiplaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_scenario", nullable = false)
    private ScenarioEntity idScenario;

    @Column(name = "testo")
    private String testo;

    @ManyToOne
    @JoinColumn(name = "id_scenario_successivo", nullable = false)
    private ScenarioEntity idScenarioSuccessivo;
}
