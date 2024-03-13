package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "indovinello")
public class IndovinelloEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_scenario", nullable = false)
    private ScenarioEntity idScenario;

    @Column(name = "testo")
    private String testo;

    @Column(name = "risposta")
    private String risposta;

    @ManyToOne
    @JoinColumn(name = "id_scenario_risposta_corretta", nullable = false)
    private ScenarioEntity idScenarioRispostaCorretta;

    @ManyToOne
    @JoinColumn(name = "id_scenario_risposta_sbagliata", nullable = false)
    private ScenarioEntity idScenarioRispostaSbagliata;
}
