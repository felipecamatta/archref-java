package com.felipecamatta.archref.domain.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("Person not found.");
    }
}
