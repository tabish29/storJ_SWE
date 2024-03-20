package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "partita")
public class PartitaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_storia", nullable = false)
    private StoriaEntity idStoria;

    @ManyToOne
    @JoinColumn(name = "id_utente", nullable = false)
    private UtenteEntity idUtente;

    @ManyToOne
    @JoinColumn(name = "id_scenario_corrente", nullable = false)
    private ScenarioEntity idScenarioCorrente;
}
