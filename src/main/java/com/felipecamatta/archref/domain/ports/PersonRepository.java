package com.felipecamatta.archref.domain.ports;

import com.felipecamatta.archref.domain.entities.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    Person save(Person person);

    List<Person> findAll();

    Optional<Person> findById(String id);

    boolean existsById(String id);

    void deleteById(String id);
}
