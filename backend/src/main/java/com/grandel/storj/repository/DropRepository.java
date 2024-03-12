package com.grandel.storj.repository;

import com.grandel.storj.entity.DropEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DropRepository extends JpaRepository<DropEntity, Long> {

    @Query("SELECT d FROM DropEntity d WHERE d.idScenario.id = :idScenario")
    DropEntity getDropByScenario(@Param("idScenario") Long idScenario);
}
