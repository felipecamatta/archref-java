package com.felipecamatta.archref.infrastructure.cache.repositories;

import com.felipecamatta.archref.infrastructure.cache.entities.PersonEntity;
import org.springframework.data.repository.CrudRepository;

public interface PersonRedisRepository extends CrudRepository<PersonEntity, String> {
}
