package com.felipecamatta.archref.domain.ports;

import com.felipecamatta.archref.domain.entities.Person;

import java.util.Optional;

public interface PersonCache {

    Person save(Person person);

    Optional<Person> findById(String id);

}
