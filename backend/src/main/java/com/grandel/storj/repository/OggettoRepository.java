package com.grandel.storj.repository;

import com.grandel.storj.entity.OggettoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OggettoRepository extends JpaRepository<OggettoEntity, Long> {

    @Query("SELECT o FROM OggettoEntity o WHERE o.idStoria.id = :idStoria")
    List<OggettoEntity> findByStoria(@Param("idStoria") Long idStoria);

}
