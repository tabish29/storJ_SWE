package com.grandel.storj.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "scenario")
public class ScenarioEntity {

    public enum TipoRispostaEnum{INDOVINELLO,MULTIPLA}
    public enum TipoScenarioEnum{INIZIALE,NORMALE,FINALE}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_storia", nullable = false)
    private StoriaEntity idStoria;

    @Column(name = "testo")
    private String testo;

    @Column(name = "tipo_risposta")
    @Enumerated(EnumType.STRING)
    private TipoRispostaEnum tipoRisposta;

    @Column(name = "tipo_scenario")
    @Enumerated(EnumType.STRING)
    private TipoScenarioEnum tipoScenario;
}
