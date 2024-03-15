package com.grandel.storj.repository;

import com.grandel.storj.entity.InventarioEntity;
import com.grandel.storj.entity.OggettoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InventarioRepository extends JpaRepository<InventarioEntity, Long> {

    @Query("SELECT i.idOggetto FROM InventarioEntity i WHERE i.idPartita.id = :idPartita")
    List<OggettoEntity> getOggettiByPartita(@Param("idPartita") Long idPartita);
}
