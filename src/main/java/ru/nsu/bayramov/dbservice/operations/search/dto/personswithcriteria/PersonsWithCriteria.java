package ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria;

import ru.nsu.bayramov.dbservice.operations.search.dto.Person;

import java.util.List;

public abstract class PersonsWithCriteria {
    private List<Person> persons;

    public PersonsWithCriteria(List<Person> persons) {
        this.persons = persons;
    }
}
