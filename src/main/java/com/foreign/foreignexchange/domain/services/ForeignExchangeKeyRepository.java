package com.foreign.foreignexchange.domain.services;

import com.foreign.foreignexchange.domain.generic.entities.ForeignKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ForeignExchangeKeyRepository extends JpaRepository<ForeignKeyEntity,ForeignKeyEntity> {
    List<ForeignKeyEntity> findAll();

    ForeignKeyEntity findByAbbreviation(String abbreviation);
}
