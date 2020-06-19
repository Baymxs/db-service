package ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria;

import ru.nsu.bayramov.dbservice.operations.search.dto.Person;

import java.util.List;

public class PersonsWithBadCustomersCountCriteria extends PersonsWithCriteria {
    private int badCustomersCountCriteria;

    public PersonsWithBadCustomersCountCriteria(List<Person> persons, int badCustomersCountCriteria) {
        super(persons);
        this.badCustomersCountCriteria = badCustomersCountCriteria;
    }
}
