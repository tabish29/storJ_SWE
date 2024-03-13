package com.grandel.storj.repository;

import com.grandel.storj.entity.IndovinelloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IndovinelloRepository extends JpaRepository<IndovinelloEntity, Long> {

    @Query("SELECT i FROM IndovinelloEntity i WHERE i.idScenario.id = :idScenario")
    IndovinelloEntity findByScenario(@Param("idScenario") Long idScenario);
}
