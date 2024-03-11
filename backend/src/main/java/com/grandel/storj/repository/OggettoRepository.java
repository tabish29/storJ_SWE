package com.grandel.storj.repository;

import com.grandel.storj.entity.OggettoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OggettoRepository extends JpaRepository<OggettoEntity, Long> {

}
