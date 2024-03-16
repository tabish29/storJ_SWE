package com.grandel.storj.repository;

import com.grandel.storj.entity.MultiplaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MultiplaRepository extends JpaRepository<MultiplaEntity, Long> {

    @Query("SELECT m FROM MultiplaEntity m WHERE m.idScenario.id = :idScenario")
    List<MultiplaEntity> findByScenario(@Param("idScenario") Long idScenario);

}
