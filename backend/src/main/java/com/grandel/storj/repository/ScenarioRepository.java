package com.grandel.storj.repository;

import com.grandel.storj.entity.ScenarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<ScenarioEntity, Long> {

}
