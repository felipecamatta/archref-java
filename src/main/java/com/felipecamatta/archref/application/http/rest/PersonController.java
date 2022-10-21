package com.felipecamatta.archref.application.http.rest;

import com.felipecamatta.archref.application.http.rest.dto.request.CreatePersonRequest;
import com.felipecamatta.archref.application.http.rest.dto.request.UpdatePersonRequest;
import com.felipecamatta.archref.application.http.rest.dto.response.CreatePersonResponse;
import com.felipecamatta.archref.application.http.rest.dto.response.GetPersonResponse;
import com.felipecamatta.archref.domain.entities.Person;
import com.felipecamatta.archref.domain.ports.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity<CreatePersonResponse> save(@Valid @RequestBody CreatePersonRequest personRequest) {
        Person person = new Person();
        BeanUtils.copyProperties(personRequest, person);
        CreatePersonResponse personResponse = new CreatePersonResponse();
        BeanUtils.copyProperties(personService.save(person), personResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
    }

    @GetMapping
    public ResponseEntity<List<GetPersonResponse>> findAll() {
        List<Person> personList = personService.findAll();
        List<GetPersonResponse> response = personList.stream()
                .map(person -> {
                    GetPersonResponse getPersonResponse = new GetPersonResponse();
                    BeanUtils.copyProperties(person, getPersonResponse);
                    return getPersonResponse;
                })
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetPersonResponse> findById(@PathVariable String id) {
        Person person = personService.findById(id);
        GetPersonResponse getPersonResponse = new GetPersonResponse();
        BeanUtils.copyProperties(person, getPersonResponse);
        return ResponseEntity.ok(getPersonResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable String id,
                                       @Valid @RequestBody UpdatePersonRequest updatePersonRequest) {
        Person person = new Person();
        BeanUtils.copyProperties(updatePersonRequest, person);
        person.setId(id);
        personService.update(person);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
