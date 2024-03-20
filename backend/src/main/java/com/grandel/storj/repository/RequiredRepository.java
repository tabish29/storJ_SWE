package com.grandel.storj.repository;

import com.grandel.storj.entity.RequiredEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequiredRepository extends JpaRepository<RequiredEntity, Long> {

    @Query("SELECT r FROM RequiredEntity r WHERE r.idScelta.id = :idScelta")
    RequiredEntity getRequiredByScelta(@Param("idScelta") Long idScelta);

}
