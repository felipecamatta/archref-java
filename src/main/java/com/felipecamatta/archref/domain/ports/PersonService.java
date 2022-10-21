package com.felipecamatta.archref.domain.ports;

import com.felipecamatta.archref.domain.entities.Character;
import com.felipecamatta.archref.domain.entities.Person;

import java.util.List;

public interface PersonService {

    Person save(Person person);

    List<Person> findAll();

    Person findById(String id);

    Person update(Person person);

    void delete(String id);

    List<Character> findAllCharacters();
}
