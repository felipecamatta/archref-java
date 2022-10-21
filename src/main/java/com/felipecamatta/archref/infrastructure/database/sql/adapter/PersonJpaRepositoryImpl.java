package com.felipecamatta.archref.infrastructure.database.sql.adapter;

import com.felipecamatta.archref.domain.entities.Person;
import com.felipecamatta.archref.domain.ports.PersonRepository;
import com.felipecamatta.archref.infrastructure.database.sql.entities.PersonEntity;
import com.felipecamatta.archref.infrastructure.database.sql.repositories.PersonJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class PersonJpaRepositoryImpl implements PersonRepository {

    private final PersonJpaRepository personJpaRepository;

    @Override
    public Person save(Person person) {
        PersonEntity personEntity = new PersonEntity();
        BeanUtils.copyProperties(person, personEntity);
        personEntity = personJpaRepository.save(personEntity);
        BeanUtils.copyProperties(personEntity, person);
        person.setId(personEntity.getId().toString());
        return person;
    }

    @Override
    public List<Person> findAll() {
        List<PersonEntity> persons = personJpaRepository.findAll();
        return persons.stream()
                .map(personEntity -> {
                    Person person = new Person();
                    BeanUtils.copyProperties(personEntity, person);
                    person.setId(personEntity.getId().toString());
                    return person;
                })
                .toList();
    }

    @Override
    public Optional<Person> findById(String id) {
        Optional<PersonEntity> personEntity = personJpaRepository.findById(Long.parseLong(id));
        if(personEntity.isPresent()) {
            Person person = new Person();
            BeanUtils.copyProperties(personEntity.get(), person);
            person.setId(personEntity.map(p -> p.getId().toString()).orElse(null));
            return Optional.of(person);
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(String id) {
        return personJpaRepository.existsById(Long.parseLong(id));
    }

    @Override
    public void deleteById(String id) {
        personJpaRepository.deleteById(Long.parseLong(id));
    }
}
