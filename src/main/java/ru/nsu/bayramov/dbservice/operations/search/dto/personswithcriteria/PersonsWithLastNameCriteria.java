package ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria;

import ru.nsu.bayramov.dbservice.operations.search.dto.Person;

import java.util.List;

public class PersonsWithLastNameCriteria extends PersonsWithCriteria {
    private String lastNameCriteria;

    public PersonsWithLastNameCriteria(List<Person> persons, String lastNameCriteria) {
        super(persons);
        this.lastNameCriteria = lastNameCriteria;
    }
}
