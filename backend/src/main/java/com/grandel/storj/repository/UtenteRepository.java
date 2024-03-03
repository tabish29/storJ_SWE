package com.grandel.storj.repository;

import com.grandel.storj.entity.UtenteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UtenteRepository extends JpaRepository<UtenteEntity, Long> {

    @Query("SELECT u FROM UtenteEntity u WHERE u.username = :username")
    List<UtenteEntity> findByUsername(@Param("username") String username);
}
