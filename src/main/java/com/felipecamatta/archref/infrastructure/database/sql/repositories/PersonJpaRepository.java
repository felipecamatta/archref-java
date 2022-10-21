package com.felipecamatta.archref.infrastructure.database.sql.repositories;

import com.felipecamatta.archref.infrastructure.database.sql.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {
}
