package com.grandel.storj.repository;

import com.grandel.storj.entity.PartitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartitaRepository extends JpaRepository<PartitaEntity, Long> {

    @Query("SELECT p FROM PartitaEntity p WHERE p.idUtente.id = :idUtente")
    List<PartitaEntity> getPartiteByUtente(@Param("idUtente") Long idUtente);

}
