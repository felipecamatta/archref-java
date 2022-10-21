package com.felipecamatta.archref.infrastructure.cache.adapter;

import com.felipecamatta.archref.domain.entities.Person;
import com.felipecamatta.archref.domain.ports.PersonCache;
import com.felipecamatta.archref.infrastructure.cache.entities.PersonEntity;
import com.felipecamatta.archref.infrastructure.cache.repositories.PersonRedisRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

@AllArgsConstructor
public class PersonRedisRepositoryImpl implements PersonCache {

    private final PersonRedisRepository personRedisRepository;

    @Override
    public Person save(Person person) {
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(person, personEntity);
        BeanUtils.copyProperties(personRedisRepository.save(personEntity), person);
        return person;
    }

    @Override
    public Optional<Person> findById(String id) {
        Optional<PersonEntity> personEntity = personRedisRepository.findById(id);
        Person person = null;
        if(personEntity.isPresent()) {
            person = new Person();
            BeanUtils.copyProperties(personEntity.get(), person);
        }
        return Optional.ofNullable(person);
    }
}
