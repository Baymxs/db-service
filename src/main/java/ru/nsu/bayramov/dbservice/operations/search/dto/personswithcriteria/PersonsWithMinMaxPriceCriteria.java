package ru.nsu.bayramov.dbservice.operations.search.dto.personswithcriteria;

import ru.nsu.bayramov.dbservice.operations.search.dto.MinMaxPrice;
import ru.nsu.bayramov.dbservice.operations.search.dto.Person;

import java.util.List;

public class PersonsWithMinMaxPriceCriteria extends PersonsWithCriteria {
    private MinMaxPrice minMaxPriceCriteria;

    public PersonsWithMinMaxPriceCriteria(List<Person> persons, MinMaxPrice minMaxPriceCriteria) {
        super(persons);
        this.minMaxPriceCriteria = minMaxPriceCriteria;
    }
}
