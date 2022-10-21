package com.felipecamatta.archref.domain.services;

import com.felipecamatta.archref.domain.entities.Character;
import com.felipecamatta.archref.domain.entities.Person;
import com.felipecamatta.archref.domain.exceptions.PersonNotFoundException;
import com.felipecamatta.archref.domain.ports.CharacterHttp;
import com.felipecamatta.archref.domain.ports.PersonCache;
import com.felipecamatta.archref.domain.ports.PersonEvent;
import com.felipecamatta.archref.domain.ports.PersonRepository;
import com.felipecamatta.archref.domain.ports.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final CharacterHttp characterHttp;
    private final PersonCache personCache;
    private final PersonEvent personEvent;
    private final PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        Person personSaved = personRepository.save(person);
        cachePerson(personSaved);
        personEvent.publishPersonChangeEvent("CREATED", personSaved.getId());
        return personSaved;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findById(String id) {
        Optional<Person> personRedis = checkRedisCache(id);
        if (personRedis.isPresent()) {
            return personRedis.get();
        }
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    @Override
    public Person update(Person person) {
        if (personRepository.existsById(person.getId())) {
            BeanUtils.copyProperties(personRepository.save(person), person);
            return person;
        } else {
            throw new PersonNotFoundException();
        }
    }

    @Override
    public void delete(String id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        } else {
            throw new PersonNotFoundException();
        }
    }

    @Override
    public List<Character> findAllCharacters() {
        return characterHttp.findAll();
    }

    private Optional<Person> checkRedisCache(String personId) {
        return personCache.findById(personId);
    }

    private void cachePerson(Person person) {
        personCache.save(person);
    }
}
