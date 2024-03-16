package com.grandel.storj.repository;

import com.grandel.storj.entity.ScenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenarioRepository extends JpaRepository<ScenarioEntity, Long> {

    @Query("SELECT s FROM ScenarioEntity s WHERE s.idStoria.id = :idStoria")
    List<ScenarioEntity> find(@Param("idStoria") Long idStoria);

    @Query("SELECT s FROM ScenarioEntity s WHERE s.idStoria.id = :idStoria AND s.tipoScenario = :tipologia")
    List<ScenarioEntity> findByTipoScenario(@Param("idStoria") Long idStoria, @Param("tipologia") ScenarioEntity.TipoScenarioEnum tipologia);

}
