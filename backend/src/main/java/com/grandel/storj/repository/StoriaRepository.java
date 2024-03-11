package com.grandel.storj.repository;

import com.grandel.storj.entity.StoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoriaRepository extends JpaRepository<StoriaEntity, Long> {

    @Query("SELECT s FROM StoriaEntity s WHERE s.idCreatore.id = :autore")
    List<StoriaEntity> findByAutore(@Param("autore") Long autore);

    @Query("SELECT s FROM StoriaEntity s WHERE s.categoria = :categoria")
    List<StoriaEntity> findByCategoria(@Param("categoria") String categoria);
}
