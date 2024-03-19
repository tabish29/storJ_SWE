package com.grandel.storj.repository;

import com.grandel.storj.entity.StoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoriaRepository extends JpaRepository<StoriaEntity, Long> {

    @Query("SELECT s FROM StoriaEntity s WHERE s.statoCompletamento = true")
    List<StoriaEntity> findAllCompleted();

    @Query("SELECT s FROM StoriaEntity s WHERE s.idCreatore.id = :idUtente")
    List<StoriaEntity> findByUtente(@Param("idUtente") Long idUtente);

    @Query("SELECT s FROM StoriaEntity s WHERE s.idCreatore.id = :autore AND s.statoCompletamento = true")
    List<StoriaEntity> findByAutore(@Param("autore") Long autore);

    @Query("SELECT s FROM StoriaEntity s WHERE LOWER(s.categoria) = LOWER(:categoria) AND s.statoCompletamento = true")
    List<StoriaEntity> findByCategoria(@Param("categoria") String categoria);

    @Query("SELECT s FROM StoriaEntity s WHERE s.numeroScenari = :numScenari AND s.statoCompletamento = true")
    List<StoriaEntity> findByScenari(@Param("numScenari") int numScenari);

}
